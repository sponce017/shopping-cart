package com.prueba.sponce.service.impl;

import com.prueba.sponce.client.ProductApiClient;
import com.prueba.sponce.dto.ProductDto;
import com.prueba.sponce.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductApiClient productApiClient;

    @Override
    public List<ProductDto> getAllProducts() {
        return productApiClient.getAllProducts();
    }

    @Override
    public ProductDto getProductById(Long id) {
        return productApiClient.getProductById(id);
    }
}