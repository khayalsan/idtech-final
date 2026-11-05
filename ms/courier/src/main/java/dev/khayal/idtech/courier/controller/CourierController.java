package dev.khayal.idtech.courier.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/couriers")
public class CourierController {

	@GetMapping("/available")
	public void getAvailableCourier() {

	}
}
