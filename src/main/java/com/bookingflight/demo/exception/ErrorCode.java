package com.bookingflight.demo.exception;

public enum ErrorCode {
    USER_EXISTED(1001, "User existed"),
    USER_NOT_EXISTED(1003, "User not existed"),
    CUSTOMER_EXISTED(1001, "Customer existed"),
    CUSTOMER_NOT_EXISTED(1003, "Customer not existed"),
    UNIDENTIFIED_EXCEPTION(9999, "Unidentified exception"),
    PASSWORD_INVALID(1002, "Password must be at least 8 characters"),
    INCORRECT_PASSWORD(1004, "Incorrect password"),
    AIRPORT_EXISTED(1001, "Airport's name existed"),
    AIRPORT_NOT_EXISTED(1003, "Airport not existed"),
    EMAIL_EXISTED(1001, "Email existed"),
    ACCOUNT_NOT_EXISTED(1003, "Account not existed"),
    EMAIL_INVALID(1005, "Email is invalid(ex:name@gmail.com)"),
    INVALID_BIRTH(1004, "Invalid birth date"),
    SEATCLASS_NOT_EXISTED(1003, "SeatClass not existed"),
    SEATCLASS_NAME_EXISTED(1003, "SeatClass name existed"),
    FLIGHT_NOT_EXISTED(1003, "Flight not existed"),
    TICKET_NOT_EXISTED(1003, "Ticket not existed"),
    INVALID_REQUEST(1005, "Invalid request"),;

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
