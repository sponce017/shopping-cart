package com.prueba.sponce.service.impl;

import com.prueba.sponce.dto.ProductDto;
import com.prueba.sponce.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final WebClient.Builder webClientBuilder;

    @Value("${fake-store.base-url}")
    private String baseUrl;

    @Override
    public Flux<ProductDto> getAllProducts() {
        return webClientBuilder.baseUrl(baseUrl)
                .build()
                .get()
                .uri("/products")
                .retrieve()
                .bodyToFlux(ProductDto.class);
    }
}
