package com.prueba.sponce.service.impl;

import com.prueba.sponce.client.ProductApiClient;
import com.prueba.sponce.dto.*;
import com.prueba.sponce.exception.OrderNotFoundException;
import com.prueba.sponce.model.*;
import com.prueba.sponce.repository.OrderRepository;
import com.prueba.sponce.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final ProductApiClient productApiClient;

    @Override
    public OrderDto createOrder(OrderRequestDto request) {
        List<OrderItem> items = new ArrayList<>();

        for (Long productId : request.getProductIds()) {
            ProductDto product = productApiClient.getProductById(productId);

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

        Order savedOrder = orderRepository.save(order);
        return convertToDto(savedOrder);
    }

    @Override
    public List<OrderDto> getAllOrders() {
        return orderRepository.findAll().stream()
                .map(this::convertToDto)
                .toList();
    }

    @Override
    public OrderDto payOrder(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException(id));

        order.setPaid(true);
        return convertToDto(orderRepository.save(order));
    }

    private OrderDto convertToDto(Order order) {
        OrderDto dto = new OrderDto();
        dto.setId(order.getId());
        dto.setPaid(order.isPaid());

        ClientDto clientDto = new ClientDto();
        clientDto.setId(order.getClient().getId());
        clientDto.setName(order.getClient().getName());
        clientDto.setEmail(order.getClient().getEmail());
        dto.setClient(clientDto);

        if (order.getOrderDetail() != null) {
            OrderDetailDto detailDto = new OrderDetailDto();
            detailDto.setId(order.getOrderDetail().getId());
            detailDto.setShippingAddress(order.getOrderDetail().getShippingAddress());
            detailDto.setNotes(order.getOrderDetail().getNotes());
            dto.setOrderDetail(detailDto);
        }

        List<OrderItemDto> itemDtos = new ArrayList<>();
        for (OrderItem item : order.getItems()) {
            OrderItemDto itemDto = new OrderItemDto();
            itemDto.setId(item.getId());
            itemDto.setProductId(item.getProductId());
            itemDto.setProductTitle(item.getProductTitle());
            itemDto.setPrice(item.getPrice());
            itemDtos.add(itemDto);
        }

        dto.setItems(itemDtos);
        return dto;
    }
}