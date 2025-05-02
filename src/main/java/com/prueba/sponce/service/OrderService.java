package com.prueba.sponce.service;

import com.prueba.sponce.dto.OrderDto;
import com.prueba.sponce.dto.OrderRequestDto;

import java.util.List;

public interface OrderService {
    OrderDto createOrder(OrderRequestDto request);
    List<OrderDto> getAllOrders();
    OrderDto payOrder(Long id);
}