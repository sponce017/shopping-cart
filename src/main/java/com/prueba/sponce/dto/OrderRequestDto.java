package com.prueba.sponce.dto;

import lombok.Data;

import java.util.List;

@Data
public class OrderRequestDto {
    private String clientName;
    private List<Long> productIds;
}
