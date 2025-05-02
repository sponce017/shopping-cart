package com.prueba.sponce.dto;

import lombok.Data;

@Data
public class OrderDetailDto {
    private Long id;
    private String shippingAddress;
    private String notes;
}
