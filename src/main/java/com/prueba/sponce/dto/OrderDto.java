package com.prueba.sponce.dto;

import lombok.Data;

import java.util.List;

@Data
public class OrderDto {
    private Long id;
    private boolean paid;
    private ClientDto client;
    private OrderDetailDto orderDetail;
    private List<OrderItemDto> items;
}
