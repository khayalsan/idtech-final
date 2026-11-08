package dev.khayal.idtech.order.exception;

public class CourierNotFoundException extends RuntimeException {
    public CourierNotFoundException() {
        super("COURIER_NOT_FOUND");
    }
    public CourierNotFoundException(String message) {
        super("COURIER_NOT_FOUND: " + message);
    }
}
