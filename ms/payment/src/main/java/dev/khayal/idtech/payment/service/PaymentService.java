package dev.khayal.idtech.payment.service;

import dev.khayal.idtech.payment.entity.PaymentEntity;
import dev.khayal.idtech.payment.event.PaymentPendingEvent;
import dev.khayal.idtech.payment.repository.CourierBalanceRepository;
import dev.khayal.idtech.payment.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

import static dev.khayal.idtech.payment.enums.PaymentStatus.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class PaymentService {
	private final PaymentRepository paymentRepository;
	private final PaymentEventProducer paymentEventProducer;

	private final CourierBalanceRepository courierBalanceRepository;

	public BigDecimal viewTotalEarnings() {
		return BigDecimal.ZERO;
//		TODO: implement
	}

	public BigDecimal viewCurrentBalance() {
		return BigDecimal.ZERO;
		// TODO: implement
	}


	@Transactional
	public void payCourier() {
		log.info("Paying courier");
		Long courierId = 1L; // TODO: get id
		var earning = calculateCourierEarning();
		var courierBalanceEntity = courierBalanceRepository.findById(courierId).orElseThrow(); // TODO: exception
		courierBalanceEntity.setAmount(courierBalanceEntity.getAmount().add(earning));
		courierBalanceEntity.setTurnover(courierBalanceEntity.getTurnover().add(earning));

		var paymentEntity = paymentRepository.findById(courierId).orElseThrow(); // TODO: Exception;
		paymentEntity.setStatus(COMPLETED);
		log.info("Paying courier {}", paymentEntity);
	}

	private BigDecimal calculateCourierEarning() {
		return BigDecimal.ONE;
		// TODO: implement
	}

	public void createPayment() {
		log.info("Creating payment");
		var paymentEntity = new PaymentEntity();
		// TODO: mapper
		paymentEntity.setStatus(PENDING);
		paymentRepository.save(paymentEntity);
		paymentEventProducer.publish(new PaymentPendingEvent(1L));
		// TODO: mapper
		log.info("Payment created");
	}

	public void finishPayment() {
		log.info("Finishing payment");
		var paymentEntity = paymentRepository.findById(1L).orElseThrow(); // TODO: Custom Exception
		paymentEntity.setStatus(PAID);
		paymentRepository.save(paymentEntity);
		paymentEventProducer.publish(new PaymentPendingEvent(1L)); // TODO: correct event -> mapper
		log.info("Payment finished");
	}

	public void viewPaymentHistory() {
		log.info("Viewing payment history");
		var courierId = 1L; // TODO: get id from request
		var paymentHistory = paymentRepository.getAllByTo(courierId);
		log.info("Viewed payment history for {}", courierId);
	}
}