package com.jhenriquedsm.btgpactual.orders.respository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.jhenriquedsm.btgpactual.orders.entity.OrderEntity;

public interface OrderRepository extends MongoRepository<OrderEntity, Long>{
    
}