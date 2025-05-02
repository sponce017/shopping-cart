package com.prueba.sponce.controller;

import com.prueba.sponce.dto.OrderDto;
import com.prueba.sponce.dto.OrderRequestDto;
import com.prueba.sponce.exception.OrderNotFoundException;
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
    public ResponseEntity<OrderDto> create(@RequestBody OrderRequestDto dto) {
        OrderDto created = orderService.createOrder(dto);
        return ResponseEntity.ok(created);
    }

    @GetMapping
    public ResponseEntity<List<OrderDto>> getAll() {
        List<OrderDto> orders = orderService.getAllOrders();
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDto> getById(@PathVariable Long id) {
        OrderDto order = orderService.getOrderById(id);
        return ResponseEntity.ok(order);
    }

    @PutMapping("/{id}/pay")
    public ResponseEntity<OrderDto> pay(@PathVariable Long id) {
        OrderDto paid = orderService.payOrder(id);
        return ResponseEntity.ok(paid);
    }
}