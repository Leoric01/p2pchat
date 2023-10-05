package com.urban.p2pchatapp.models;

public class MessageResult {
    private final boolean success;
    private final String error;

    public MessageResult(boolean success, String error) {
        this.success = success;
        this.error = error;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getError() {
        return error;
    }
}
