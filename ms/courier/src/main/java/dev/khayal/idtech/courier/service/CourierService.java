package dev.khayal.idtech.courier.service;

import dev.khayal.idtech.courier.dto.CourierRequest;
import dev.khayal.idtech.courier.exception.CourierNotFoundException;
import dev.khayal.idtech.courier.mapper.CourierMapper;
import dev.khayal.idtech.courier.repository.CourierRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static dev.khayal.idtech.courier.enums.CourierStatus.BUSY;
import static dev.khayal.idtech.courier.enums.CourierStatus.FREE;

@Service
@RequiredArgsConstructor
public class CourierService {
	private final CourierRepository courierRepository;
	private final CourierMapper courierMapper;

	public void add(CourierRequest courierRequest) {
		var courier = courierMapper.toEntity(courierRequest);
		courierRepository.save(courier);
	}


	public void markBusy(Long id) {
		var courier = courierRepository.findById(id).orElseThrow(CourierNotFoundException::new);
		courier.setStatus(BUSY);
		courierRepository.save(courier);
	}

	public void markFree(Long id) {
		var courier = courierRepository.findById(id).orElseThrow(CourierNotFoundException::new);
		courier.setStatus(FREE);
		courierRepository.save(courier);
	}

	public Long getAvailable() {
		return courierRepository.findFirstByStatus(FREE).orElseThrow(CourierNotFoundException::new).getId();
	}
}
