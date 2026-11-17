package dev.khayal.idtech.courier.service;

import dev.khayal.idtech.courier.dto.CourierRequest;
import dev.khayal.idtech.courier.dto.CourierResponse;
import dev.khayal.idtech.courier.entity.CourierEntity;
import dev.khayal.idtech.courier.enums.CourierStatus;
import dev.khayal.idtech.courier.exception.CourierNotFoundException;
import dev.khayal.idtech.courier.mapper.CourierMapper;
import dev.khayal.idtech.courier.repository.CourierRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static dev.khayal.idtech.courier.enums.CourierStatus.BUSY;
import static dev.khayal.idtech.courier.enums.CourierStatus.FREE;

@Service
@RequiredArgsConstructor
@Slf4j
public class CourierService {
	private final CourierRepository courierRepository;
	private final CourierMapper courierMapper;

	public void add(CourierRequest courierRequest) {
		log.info("Order Add Start");
		var courier = courierMapper.toEntity(courierRequest);
		courierRepository.save(courier);
		log.info("Order Added: id={}", courier.getId());
	}


	public void markBusy(Long id) {
		log.info("Order Mark Busy Start: id={}", id);
		var courier = courierRepository.findByIdAndStatus(id, FREE).orElseThrow(CourierNotFoundException::new);
		courier.setStatus(BUSY);
		courierRepository.save(courier);
		log.info("Order Marked Busy: id={}", courier.getId());
	}

	public void markFree(Long id) {
		log.info("Order Mark Free Start: id={}", id);
		var courier = courierRepository.findByIdAndStatus(id, BUSY).orElseThrow(CourierNotFoundException::new);
		courier.setStatus(FREE);
		courierRepository.save(courier);
		log.info("Order Marked Free: id={}", courier.getId());
	}

	public CourierResponse getAvailable() {
		return courierMapper.toResponse(courierRepository.findFirstByStatus(FREE).orElseThrow(CourierNotFoundException::new));
	}
}
