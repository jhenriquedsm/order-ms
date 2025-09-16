package com.jhenriquedsm.btgpactual.orders.controller;

import org.springframework.web.bind.annotation.RestController;

import com.jhenriquedsm.btgpactual.orders.dtos.ApiResponse;
import com.jhenriquedsm.btgpactual.orders.dtos.OrderResponse;
import com.jhenriquedsm.btgpactual.orders.dtos.PaginationResponse;
import com.jhenriquedsm.btgpactual.orders.service.OrderService;

import java.util.Map;

import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class OrderController {
    
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/customers/{customerId}/orders")
    public ResponseEntity<ApiResponse<OrderResponse>> listOrders(@PathVariable("customerId") Long customerId, @RequestParam(name = "page", defaultValue = "0") Integer page, 
    @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize) {
        
        var pageResponse = orderService.findAllByCustomerId(customerId, PageRequest.of(page, pageSize));
        var totalOnOrders = orderService.findTotalOnOrdersByCustomerId(customerId);

        return ResponseEntity.ok(new ApiResponse<>(
            Map.of("totalOnOrders", totalOnOrders),
            pageResponse.getContent(),
            PaginationResponse.toPage(pageResponse)
        ));
    }
}