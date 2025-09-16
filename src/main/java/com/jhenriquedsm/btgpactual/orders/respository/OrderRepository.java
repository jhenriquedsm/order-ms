package com.jhenriquedsm.btgpactual.orders.respository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.jhenriquedsm.btgpactual.orders.entity.OrderEntity;

public interface OrderRepository extends MongoRepository<OrderEntity, Long>{

    Page<OrderEntity> findAllByCustomerId(Long customerId, PageRequest pageRequest);
    
}