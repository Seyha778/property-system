package com.apextech.property.senghang.common;

public class ResponseMessageBuilder<T> {

    private boolean success;
    private String message;
    private String devMessage;
    private T data;

    public ResponseMessageBuilder<T> success() {
        this.success = true;
        this.message = "Success";
        return this;
    }

    public ResponseMessageBuilder<T> error(String message) {
        this.success = false;
        this.message = message;
        return this;
    }

    public ResponseMessageBuilder<T> devMessage(String devMessage) {
        this.devMessage = devMessage;
        return this;
    }

    public ResponseMessageBuilder<T> data(T data) {
        this.data = data;
        return this;
    }

    public ResponseMessage<T> build() {
        return ResponseMessage.<T>builder()
                .success(success)
                .message(message)
                .devMessage(devMessage)
                .data(data)
                .build();
    }
}