package com.example.transactioneventtest.order.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "ORDERS")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "order_item_id")
    private Long orderItemId;

    @Column(name = "order_quantity")
    private Integer orderQuantity;

    @Column(name = "remark")
    private String remark;

    @Builder
    public Orders(Long id, Long orderItemId, Integer orderQuantity, String remark) {
        this.id = id;
        this.orderItemId = orderItemId;
        this.orderQuantity = orderQuantity;
        this.remark = remark;
    }
}
