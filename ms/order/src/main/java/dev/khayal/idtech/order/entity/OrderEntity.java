package dev.khayal.idtech.order.entity;

import dev.khayal.idtech.order.enums.OrderStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
@Getter @Setter
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 2, max = 100)
    @Column(nullable = false, length = 100)
    private String pickupAddress;
    @Size(min = 2, max = 100)
    @Column(nullable = false, length = 100)
    private String deliveryAddress;

    private Long courierId;
    private OrderStatus status;
    private BigDecimal price;

    @OneToMany(
        cascade = CascadeType.ALL,
        orphanRemoval = true
    )
    @JoinColumn(name = "order_id")
    private List<OrderItemEntity> items = new ArrayList<>();
}
