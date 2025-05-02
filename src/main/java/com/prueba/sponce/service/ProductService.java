package com.prueba.sponce.service;

import com.prueba.sponce.dto.ProductDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProductService {
    Flux<ProductDto> getAllProducts();
    Mono<ProductDto> getProductById(Long id);
}
