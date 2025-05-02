package com.prueba.sponce.controller;

import com.prueba.sponce.dto.OrderRequestDto;
import com.prueba.sponce.model.Order;
import com.prueba.sponce.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public Order create(@RequestBody OrderRequestDto dto) {
        return orderService.createOrder(dto);
    }

    @GetMapping
    public List<Order> getAll() {
        return orderService.getAllOrders();
    }

    @PutMapping("/{id}/pay")
    public Order pay(@PathVariable Long id) {
        return orderService.payOrder(id);
    }
}
