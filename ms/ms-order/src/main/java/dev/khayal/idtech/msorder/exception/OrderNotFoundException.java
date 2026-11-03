package dev.khayal.idtech.msorder.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class OrderNotFoundException extends RuntimeException {
	public OrderNotFoundException() {
		super("ORDER_NOT_FOUND");
	}
	public OrderNotFoundException(String message) {
		super("ORDER_NOT_FOUND: " + message);
	}
}
