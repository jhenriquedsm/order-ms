package com.jhenriquedsm.btgpactual.dtos;

import java.util.List;

public record OrderCreatedEvent(Long codigoPedido, Long codigoCliente, List<OrderItemEvent> itens) {
    
}
