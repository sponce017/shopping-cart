package com.prueba.sponce.service.impl;

import com.prueba.sponce.client.ProductApiClient;
import com.prueba.sponce.dto.ProductDto;
import com.prueba.sponce.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductApiClient productApiClient;

    @Override
    public Flux<ProductDto> getAllProducts() {
        return productApiClient.getAllProducts();
    }

    @Override
    public Mono<ProductDto> getProductById(Long id) {
        return productApiClient.getProductById(id);
    }
}