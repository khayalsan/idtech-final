package dev.khayal.idtech.courier.controller;

import dev.khayal.idtech.courier.dto.CourierRequest;
import dev.khayal.idtech.courier.service.CourierService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController("/couriers")
@RequiredArgsConstructor
public class CourierController {
	private final CourierService courierService;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public void add(@RequestBody CourierRequest courierRequest) {
		courierService.add(courierRequest);
	}

	@GetMapping("/available")
	public Long getAvailable() {
		return courierService.getAvailable();
	}
}
