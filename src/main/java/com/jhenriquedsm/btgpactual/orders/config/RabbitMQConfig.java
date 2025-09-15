package com.jhenriquedsm.btgpactual.orders.config;

import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    public static final String ORDER_CREATED_QUEUE = "btg-pactual-order-created";
}
