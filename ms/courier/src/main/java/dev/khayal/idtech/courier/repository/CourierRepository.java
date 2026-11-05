package dev.khayal.idtech.courier.repository;

import dev.khayal.idtech.courier.entity.CourierEntity;
import dev.khayal.idtech.courier.enums.CourierStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CourierRepository extends JpaRepository<CourierEntity, Long> {
    Optional<CourierEntity> findFirstByStatus(CourierStatus status);
}
