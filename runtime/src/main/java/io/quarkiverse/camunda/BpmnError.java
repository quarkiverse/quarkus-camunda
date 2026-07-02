package io.quarkiverse.camunda;

public class BpmnError extends RuntimeException {

    private String errorCode;
    private String errorMessage;

    public BpmnError(String errorCode, String errorMessage) {
        super("[" + errorCode + "] " + errorMessage);
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public String getErrorCode() {
        return errorCode;
    }

}
