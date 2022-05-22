package com.example.validationexample.dto;

import java.util.List;

public class ErrorResponse {
    String statusCode;
    String requestUrl;
    String message;
    String resultCode;

    List<ErrorDetail> errorDetailList;

    public ErrorResponse(String statusCode, String requestUrl, String message, String resultCode) {
        this.statusCode = statusCode;
        this.requestUrl = requestUrl;
        this.message = message;
        this.resultCode = resultCode;
    }

    public ErrorResponse(String statusCode, String requestUrl, String message, String resultCode,
                         List<ErrorDetail> errorDetailList) {
        this(statusCode, requestUrl, message, resultCode);
        this.errorDetailList = errorDetailList;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getRequestUrl() {
        return requestUrl;
    }

    public void setRequestUrl(String requestUrl) {
        this.requestUrl = requestUrl;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public List<ErrorDetail> getErrorList() {
        return errorDetailList;
    }

    public void setErrorList(List<ErrorDetail> errorDetailList) {
        this.errorDetailList = errorDetailList;
    }

    @Override
    public String toString() {
        return "ErrorResponse{" +
                "statusCode='" + statusCode + '\'' +
                ", requestUrl='" + requestUrl + '\'' +
                ", message='" + message + '\'' +
                ", resultCode='" + resultCode + '\'' +
                ", errorList=" + errorDetailList +
                '}';
    }
}
