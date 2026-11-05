package dev.khayal.idtech.courier.controller;

import dev.khayal.idtech.courier.service.CourierService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/couriers")
@RequiredArgsConstructor
public class CourierController {
	private final CourierService courierService;

	@GetMapping("/available")
	public Long getAvailable() {
		return courierService.getAvailable();
	}
}
