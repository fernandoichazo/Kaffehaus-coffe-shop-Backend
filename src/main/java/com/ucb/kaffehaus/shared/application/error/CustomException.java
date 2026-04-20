package com.ucb.kaffehaus.shared.application.error;

public class CustomException extends RuntimeException {
    private final int statusCode;

    public CustomException(int statusCode, String message) {
        super(message);
        this.statusCode = statusCode;
    }

    public int getStatusCode() { return statusCode; }

    public static CustomException badRequest(String message) {
        return new CustomException(400, message);
    }

    public static CustomException unauthorized(String message) {
        return new CustomException(401, message);
    }

    public static CustomException forbidden(String message) {
        return new CustomException(403, message);
    } 

    public static CustomException notFound(String message) {
        return new CustomException(404, message);
    }

    public static CustomException internalServer(String message) {
        return new CustomException(500, message);
    }

    public static CustomException custom(Integer number, String message) {
        return new CustomException(number, message);
    }
}
