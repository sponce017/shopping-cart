package com.prueba.sponce.service;

import com.prueba.sponce.dto.ProductDto;

import java.util.List;

public interface ProductService {
    List<ProductDto> getAllProducts();
    ProductDto getProductById(Long id);
}