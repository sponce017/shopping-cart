package com.prueba.sponce.service;

import com.prueba.sponce.dto.ProductDto;
import reactor.core.publisher.Flux;

public interface ProductService {
    Flux<ProductDto> getAllProducts();
}
