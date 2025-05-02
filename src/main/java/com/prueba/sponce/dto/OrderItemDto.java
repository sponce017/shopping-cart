package com.prueba.sponce.dto;

import lombok.Data;

@Data
public class OrderItemDto {
    private Long id;
    private Long productId;
    private String productTitle;
    private Double price;
}