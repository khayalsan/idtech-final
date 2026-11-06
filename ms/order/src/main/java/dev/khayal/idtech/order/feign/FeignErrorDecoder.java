package dev.khayal.idtech.order.feign;

import dev.khayal.idtech.order.exception.CourierNotFoundException;import feign.Response;import feign.codec.ErrorDecoder;

public class FeignErrorDecoder implements ErrorDecoder {
    private static final ErrorDecoder defaultDecoder = new Default();
    @Override
    public Exception decode(String s, Response response) {
        return switch (response.status()) {
            case 404 -> new CourierNotFoundException();
            default -> defaultDecoder.decode(s, response);
        };
    }
}