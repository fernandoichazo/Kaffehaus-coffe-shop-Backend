package com.ucb.kaffehaus.shared.application.error;

public class CustomException extends RuntimeException {
    private final int statusCode;

    public CustomException(int statusCode, String message) {
        super(message);
        this.statusCode = statusCode;
    }

    public int getStatusCode() { return statusCode; }

    // Métodos estáticos idénticos a tu clase de TS
    public static CustomException badRequest(String message) {
        return new CustomException(400, message);
    }

    public static CustomException notFound(String message) {
        return new CustomException(404, message);
    }

    public static CustomException internalServer(String message) {
        return new CustomException(500, message);
    }
}
