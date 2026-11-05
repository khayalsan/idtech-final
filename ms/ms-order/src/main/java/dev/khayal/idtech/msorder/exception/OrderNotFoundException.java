package dev.khayal.idtech.msorder.exception;

public class OrderNotFoundException extends RuntimeException {
	public OrderNotFoundException() {
		super("ORDER_NOT_FOUND");
	}
	public OrderNotFoundException(String message) {
		super("ORDER_NOT_FOUND: " + message);
	}
}
