package com.bookingflight.demo.exception;

public enum ErrorCode {
    USER_EXISTED(1001, "User existed"),
    UNIDENTIFIED_EXCEPTION(9999, "Unidentified exception"),
    PASSWORD_INVALID(1002, "Password must be at least 3 characters");

    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    private int code;
    private String message;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

}
