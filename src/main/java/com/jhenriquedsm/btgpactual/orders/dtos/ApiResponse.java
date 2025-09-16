package com.jhenriquedsm.btgpactual.orders.dtos;

import java.util.List;

public record ApiResponse<T>(List<T> data, PaginationResponse paginationResponse) {
    
}