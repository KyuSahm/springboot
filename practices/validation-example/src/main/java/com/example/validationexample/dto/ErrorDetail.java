package com.example.validationexample.dto;

public class ErrorDetail {
    private String field;
    private String message;
    private String invalidValue;

    public ErrorDetail(String field, String message, String invalidValue) {
        this.field = field;
        this.message = message;
        this.invalidValue = invalidValue;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getInvalidValue() {
        return invalidValue;
    }

    public void setInvalidValue(String invalidValue) {
        this.invalidValue = invalidValue;
    }

    @Override
    public String toString() {
        return "ErrorDetail{" +
                "field='" + field + '\'' +
                ", message='" + message + '\'' +
                ", invalidValue='" + invalidValue + '\'' +
                '}';
    }
}
