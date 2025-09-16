package com.jhenriquedsm.btgpactual.orders.dtos;

import org.springframework.data.domain.Page;

public record PaginationResponse(Integer page, Integer pageSize, Long totalElements, Integer totalPages) {

    public static PaginationResponse toPage(Page<?> page) {
        return new PaginationResponse(page.getNumber(), page.getSize(), page.getTotalElements(), page.getTotalPages());
    }
}