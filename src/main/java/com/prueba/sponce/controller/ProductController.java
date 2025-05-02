package com.prueba.sponce.controller;

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
    public ResponseEntity<Flux<ProductDto>> getAll() {
        Flux<ProductDto> products = productService.getAllProducts();
        return ResponseEntity.ok()
                .header("X-Service-Source", "FakeStoreAPI")
                .body(products);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Mono<ProductDto>> getById(@PathVariable Long id) {
        Mono<ProductDto> product = productService.getProductById(id);
        return ResponseEntity.ok(product);
    }
}