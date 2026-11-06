package dev.khayal.idtech.payment.service;

import dev.khayal.idtech.payment.entity.PaymentEntity;
import dev.khayal.idtech.payment.event.PaymentPendingEvent;
import dev.khayal.idtech.payment.repository.CourierBalanceRepository;
import dev.khayal.idtech.payment.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

import static dev.khayal.idtech.payment.enums.PaymentStatus.*;

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

	public void payCourier() {
		Long courierId = 1L; // TODO: get id
		var earning = calculateCourierEarning();
		var courierBalanceEntity = courierBalanceRepository.findById(courierId).orElseThrow(); // TODO: exception
		courierBalanceEntity.setAmount(courierBalanceEntity.getAmount().add(earning));
		courierBalanceEntity.setTurnover(courierBalanceEntity.getTurnover().add(earning));

		var paymentEntity = paymentRepository.findById(courierId).orElseThrow(); // TODO: Exception;
		paymentEntity.setStatus(COMPLETED);

		paymentRepository.save(paymentEntity);
		courierBalanceRepository.save(courierBalanceEntity);
	}

	private BigDecimal calculateCourierEarning() {
		return BigDecimal.ONE;
		// TODO: implement
	}

	public void createPayment() {
		var paymentEntity = new PaymentEntity();
		// TODO: mapper
		paymentEntity.setStatus(PENDING);
		paymentRepository.save(paymentEntity);
		paymentEventProducer.publish(new PaymentPendingEvent(1L));
		// TODO: mapper
	}

	public void finishPayment() {
		var paymentEntity = paymentRepository.findById(1L).orElseThrow(); // TODO: Custom Exception
		paymentEntity.setStatus(PAID);
		paymentRepository.save(paymentEntity);
		paymentEventProducer.publish(new PaymentPendingEvent(1L)); // TODO: correct event -> mapper
	}

	public void viewPaymentHistory() {
		var courierId = 1L; // TODO: get id from request
		var paymentHistory = paymentRepository.getAllByTo(courierId);
	}

}
