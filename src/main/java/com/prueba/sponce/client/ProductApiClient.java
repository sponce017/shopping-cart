package com.prueba.sponce.client;

import com.prueba.sponce.dto.ProductDto;
import com.prueba.sponce.exception.ProductNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException.NotFound;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ProductApiClient {

    @Value("${fake-store.base-url}")
    private String baseUrl;

    private final RestTemplate restTemplate = new RestTemplate();

    public List<ProductDto> getAllProducts() {
        ResponseEntity<ProductDto[]> response = restTemplate.getForEntity(baseUrl + "/products", ProductDto[].class);
        return Arrays.asList(response.getBody());
    }

    public ProductDto getProductById(Long id) {
        try {
            return restTemplate.getForObject(baseUrl + "/products/" + id, ProductDto.class);
        } catch (NotFound ex) {
            throw new ProductNotFoundException(id);
        }
    }
}