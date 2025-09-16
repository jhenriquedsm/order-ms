package com.jhenriquedsm.btgpactual.orders.dtos;

import java.math.BigDecimal;

import com.jhenriquedsm.btgpactual.orders.entity.OrderEntity;

public record OrderResponse(Long orderId, Long customerId, BigDecimal total) {

    public static OrderResponse toEntity(OrderEntity orderEntity) {
        return new OrderResponse(orderEntity.getOrderId(), orderEntity.getCustomerId(), orderEntity.getTotal());
    }
}