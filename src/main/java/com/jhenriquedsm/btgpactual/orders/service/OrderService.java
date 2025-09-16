package com.jhenriquedsm.btgpactual.orders.service;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import com.jhenriquedsm.btgpactual.orders.dtos.OrderCreatedEvent;
import com.jhenriquedsm.btgpactual.orders.entity.OrderEntity;
import com.jhenriquedsm.btgpactual.orders.entity.OrderItem;
import com.jhenriquedsm.btgpactual.orders.respository.OrderRepository;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }
    
    public void save(OrderCreatedEvent event) {
        var entity = new OrderEntity();
        entity.setOrderId(event.codigoPedido());
        entity.setCustomerId(event.codigoCliente());

        entity.setItens(event.itens().stream()
        .map(item -> new OrderItem(item.produto(), item.quantidade(), item.preco()))
        .toList());

        entity.setTotal(event.itens().stream()
        .map(item -> item.preco().multiply(BigDecimal.valueOf(item.quantidade())))
        .reduce(BigDecimal::add)
        .orElse(BigDecimal.ZERO));

        orderRepository.save(entity);
    }   
}