package com.prueba.sponce.service.impl;

import com.prueba.sponce.dto.OrderRequestDto;
import com.prueba.sponce.dto.ProductDto;
import com.prueba.sponce.model.Order;
import com.prueba.sponce.model.OrderItem;
import com.prueba.sponce.repository.OrderRepository;
import com.prueba.sponce.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final WebClient.Builder webClientBuilder;

    @Value("${fake-store.base-url}")
    private String baseUrl;

    @Override
    public Order createOrder(OrderRequestDto request) {
        List<OrderItem> items = new ArrayList<>();

        for (Long productId : request.getProductIds()) {
            ProductDto product = webClientBuilder.baseUrl(baseUrl).build()
                    .get()
                    .uri("/products/{id}", productId)
                    .retrieve()
                    .bodyToMono(ProductDto.class)
                    .block();

            items.add(OrderItem.builder()
                    .productId(product.getId())
                    .productTitle(product.getTitle())
                    .price(product.getPrice())
                    .build());
        }

        Order order = Order.builder()
                .clientName(request.getClientName())
                .paid(false)
                .items(items)
                .build();

        items.forEach(item -> item.setOrder(order));

        return orderRepository.save(order);
    }

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public Order payOrder(Long id) {
        Order order = orderRepository.findById(id).orElseThrow();
        order.setPaid(true);
        return orderRepository.save(order);
    }
}
