package dev.khayal.idtech.payment.repository;

import dev.khayal.idtech.payment.entity.CourierBalanceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourierBalanceRepository extends JpaRepository<CourierBalanceEntity, Long> {
}
