package com.prueba.sponce.controller;

import com.prueba.sponce.dto.ApiResponse;
import com.prueba.sponce.dto.OrderDto;
import com.prueba.sponce.dto.OrderRequestDto;
import com.prueba.sponce.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<ApiResponse<OrderDto>> create(@RequestBody OrderRequestDto dto) {
        OrderDto created = orderService.createOrder(dto);
        return ResponseEntity.ok(new ApiResponse<>("Order created successfully", created));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<OrderDto>>> getAll() {
        List<OrderDto> orders = orderService.getAllOrders();
        return ResponseEntity.ok(new ApiResponse<>("Orders retrieved successfully", orders));
    }

    @PutMapping("/{id}/pay")
    public ResponseEntity<ApiResponse<OrderDto>> pay(@PathVariable Long id) {
        OrderDto paid = orderService.payOrder(id);
        return ResponseEntity.ok(new ApiResponse<>("Order paid successfully", paid));
    }
}