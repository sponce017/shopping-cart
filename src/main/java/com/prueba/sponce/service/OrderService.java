package com.prueba.sponce.service;

import com.prueba.sponce.dto.OrderRequestDto;
import com.prueba.sponce.model.Order;

import java.util.List;

public interface OrderService {
    Order createOrder(OrderRequestDto request);
    List<Order> getAllOrders();
    Order payOrder(Long id);
}
