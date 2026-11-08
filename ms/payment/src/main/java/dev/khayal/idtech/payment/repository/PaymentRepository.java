package dev.khayal.idtech.payment.repository;

import dev.khayal.idtech.payment.entity.PaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository<PaymentEntity, Long> {
	Object getAllByTo(Long to);
}
