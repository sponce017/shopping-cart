package com.prueba.sponce.controller;

import com.prueba.sponce.dto.ApiResponse;
import com.prueba.sponce.dto.ProductDto;
import com.prueba.sponce.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public Mono<ResponseEntity<ApiResponse<Flux<ProductDto>>>> getAll() {
        Flux<ProductDto> products = productService.getAllProducts();
        return Mono.just(ResponseEntity.ok(new ApiResponse<>("Products retrieved successfully", products)));
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<ApiResponse<Mono<ProductDto>>>> getById(@PathVariable Long id) {
        Mono<ProductDto> product = productService.getProductById(id);
        return Mono.just(ResponseEntity.ok(new ApiResponse<>("Product retrieved successfully", product)));
    }
}