package dev.khayal.idtech.courier.service;

import dev.khayal.idtech.courier.mapper.CourierMapper;
import dev.khayal.idtech.courier.repository.CourierRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CourierService {
	private final CourierRepository courierRepository;
	private final CourierMapper courierMapper;
}
