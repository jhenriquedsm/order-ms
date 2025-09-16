package com.jhenriquedsm.btgpactual.orders.service;

import java.math.BigDecimal;

import org.bson.Document;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;

import com.jhenriquedsm.btgpactual.orders.dtos.OrderCreatedEvent;
import com.jhenriquedsm.btgpactual.orders.dtos.OrderResponse;
import com.jhenriquedsm.btgpactual.orders.entity.OrderEntity;
import com.jhenriquedsm.btgpactual.orders.entity.OrderItem;
import com.jhenriquedsm.btgpactual.orders.respository.OrderRepository;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final MongoTemplate mongoTemplate;

    public OrderService(OrderRepository orderRepository, MongoTemplate mongoTemplate) {
        this.orderRepository = orderRepository;
        this.mongoTemplate = mongoTemplate;
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
    
    public Page<OrderResponse> findAllByCustomerId(Long customerId, PageRequest pageRequest) {
        var orders = orderRepository.findAllByCustomerId(customerId, pageRequest);

        return orders.map(OrderResponse::toEntity);
    }

    public BigDecimal findTotalOnOrdersByCustomerId(Long customerId) {
        var aggregations = newAggregation(
            match(Criteria.where("customerId").is(customerId)),
            group().sum("total").as("total")
        );

        var response = mongoTemplate.aggregate(aggregations, "tb_orders", Document.class);

        return new BigDecimal(response.getUniqueMappedResult().get("total").toString());
    }
}