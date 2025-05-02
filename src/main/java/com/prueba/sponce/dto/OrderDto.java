package com.prueba.sponce.dto;

import lombok.Data;

import java.util.List;

@Data
public class OrderDto {
    private Long id;
    private boolean paid;
    private ClientDto client;
    private List<OrderItemDto> items;
    private OrderDetailDto orderDetail;
}
