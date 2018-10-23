package com.project.reactive.reactiveservice.model.response;

public class CustomerResponse {
    public String statusMessage;
    public String statusCode;
    public String errorMsg;
    public String errorCode;
    public String txDate;

    public CustomerResponse() {
    }

    public CustomerResponse(String statusMessage, String statusCode, String errorMsg, String errorCode, String txDate) {
        this.statusMessage = statusMessage;
        this.statusCode = statusCode;
        this.errorMsg = errorMsg;
        this.errorCode = errorCode;
        this.txDate = txDate;
    }

    public String getStatusMessage() {
        return statusMessage;
    }

    public void setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getTxDate() {
        return txDate;
    }

    public void setTxDate(String txDate) {
        this.txDate = txDate;
    }
}
