package dev.khayal.idtech.order.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "couriers", url = "${services.courier.url}")
public interface CourierClient {
    @RequestMapping(method = RequestMethod.GET, value = "/available")
    Long getAvailable();
}
