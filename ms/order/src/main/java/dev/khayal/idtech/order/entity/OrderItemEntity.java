package dev.khayal.idtech.order.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;

@Entity
@Table(name = "order_items")
public class OrderItemEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private Long itemId;
    @Positive
    @Column(nullable = false)
    private Integer quantity;

    public boolean equals(Object object) {
        if (object == null || getClass() != object.getClass()) return false;
        if (!super.equals(object)) return false;
        OrderItemEntity that = (OrderItemEntity) object;
        return java.util.Objects.equals(id, that.id);
    }

    public int hashCode() {
        return Objects.hash(super.hashCode(), id);
    }
}
