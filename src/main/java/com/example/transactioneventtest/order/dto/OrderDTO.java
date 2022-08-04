package com.example.transactioneventtest.order.dto;

import com.example.transactioneventtest.order.domain.Order;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class OrderDTO {

    private Long id;

    private Long orderItemId;

    private Integer orderQuantity;

    private String remark;

    @Builder
    public OrderDTO(Long id, Long orderItemId, Integer orderQuantity, String remark) {
        this.id = id;
        this.orderItemId = orderItemId;
        this.orderQuantity = orderQuantity;
        this.remark = remark;
    }

    public Order toEntity() {
        return Order.builder()
                .orderItemId(this.orderItemId)
                .orderQuantity(this.orderQuantity)
                .remark(this.remark)
                .build();
    }

}
