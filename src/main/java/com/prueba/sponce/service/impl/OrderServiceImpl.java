package com.prueba.sponce.service.impl;

import com.prueba.sponce.dto.*;
import com.prueba.sponce.model.*;
import com.prueba.sponce.repository.OrderRepository;
import com.prueba.sponce.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

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

        Client client = Client.builder()
                .name(request.getClient().getName())
                .email(request.getClient().getEmail())
                .build();

        Order order = Order.builder()
                .client(client)
                .paid(false)
                .items(items)
                .build();

        OrderDetail detail = OrderDetail.builder()
                .shippingAddress(request.getOrderDetail().getShippingAddress())
                .notes(request.getOrderDetail().getNotes())
                .order(order)
                .build();

        order.setOrderDetail(detail);
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