package dev.khayal.idtech.courier.service;

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

	public void markBusy(Long id) {
		var courier = courierRepository.findById(id).orElseThrow(); // TODO: Exception
		courier.setStatus(BUSY);
		courierRepository.save(courier);
	}

	public void markFree(Long id) {
		var courier = courierRepository.findById(id).orElseThrow(); // TODO: Exception
		courier.setStatus(FREE);
		courierRepository.save(courier);
	}

	public Long getAvailable() {
		// TODO: Exception
		return courierRepository.findFirstByStatus(FREE).orElseThrow().getId();
	}
}
