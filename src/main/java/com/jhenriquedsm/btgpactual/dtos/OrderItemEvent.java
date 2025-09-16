package com.jhenriquedsm.btgpactual.dtos;

import java.math.BigDecimal;

public record OrderItemEvent(String produto, Integer quantidade, BigDecimal preco) {

}
