package io.quarkiverse.camunda.runtime.graal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BooleanSupplier;
import java.util.function.UnaryOperator;

import org.apache.hc.client5.http.async.methods.InflatingAsyncDataConsumer;
import org.apache.hc.client5.http.async.methods.InflatingGzipDataConsumer;
import org.apache.hc.client5.http.async.methods.InflatingZstdDataConsumer;
import org.apache.hc.client5.http.entity.compress.ContentCoding;
import org.apache.hc.client5.http.impl.ZstdRuntime;
import org.apache.hc.core5.http.config.Lookup;
import org.apache.hc.core5.http.config.RegistryBuilder;
import org.apache.hc.core5.http.nio.AsyncDataConsumer;

import com.oracle.svm.core.annotate.Alias;
import com.oracle.svm.core.annotate.Substitute;
import com.oracle.svm.core.annotate.TargetClass;

/**
 * Same as the original constructor, but without registering the brotli decoder, whose
 * {@code InflatingBrotliDataConsumer} cannot be linked at image build time without brotli4j.
 */
@TargetClass(className = "org.apache.hc.client5.http.impl.async.ContentCompressionAsyncExec", onlyWith = Brotli4jMissingSelector.class)
final class Target_org_apache_hc_client5_http_impl_async_ContentCompressionAsyncExec {

    @Alias
    private Lookup<UnaryOperator<AsyncDataConsumer>> decoders;

    @Alias
    private List<String> acceptTokens;

    @Alias
    private int maxCodecListLen;

    @Substitute
    public Target_org_apache_hc_client5_http_impl_async_ContentCompressionAsyncExec(final int maxCodecListLen) {
        final RegistryBuilder<UnaryOperator<AsyncDataConsumer>> builder = RegistryBuilder
                .<UnaryOperator<AsyncDataConsumer>> create()
                .register(ContentCoding.GZIP.token(), InflatingGzipDataConsumer::new)
                .register(ContentCoding.X_GZIP.token(), InflatingGzipDataConsumer::new)
                .register(ContentCoding.DEFLATE.token(), d -> new InflatingAsyncDataConsumer(d, null));
        final List<String> tokens = new ArrayList<>(Arrays.asList("gzip", "x-gzip", "deflate"));
        if (ZstdRuntime.available()) {
            builder.register(ContentCoding.ZSTD.token(), InflatingZstdDataConsumer::new);
            tokens.add("zstd");
        }
        this.decoders = builder.build();
        this.acceptTokens = tokens;
        this.maxCodecListLen = maxCodecListLen;
    }
}

final class Brotli4jMissingSelector implements BooleanSupplier {

    @Override
    public boolean getAsBoolean() {
        try {
            Class.forName("com.aayushatharva.brotli4j.decoder.DecoderJNI");
            return false;
        } catch (ClassNotFoundException e) {
            return true;
        }
    }
}

public class BrotliSubstitutions {

}
