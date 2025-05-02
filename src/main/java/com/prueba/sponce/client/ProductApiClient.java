package com.prueba.sponce.client;

import com.prueba.sponce.dto.ProductDto;
import com.prueba.sponce.exception.ProductNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class ProductApiClient {

    private final WebClient.Builder webClientBuilder;

    @Value("${fake-store.base-url}")
    private String baseUrl;

    public Flux<ProductDto> getAllProducts() {
        return webClientBuilder.baseUrl(baseUrl)
                .build()
                .get()
                .uri("/products")
                .retrieve()
                .bodyToFlux(ProductDto.class);
    }

    public Mono<ProductDto> getProductById(Long id) {
        return webClientBuilder.baseUrl(baseUrl)
                .build()
                .get()
                .uri("/products/{id}", id)
                .retrieve()
                .bodyToMono(ProductDto.class)
                .onErrorResume(WebClientResponseException.NotFound.class,
                        ex -> Mono.error(new ProductNotFoundException(id)));
    }
}
