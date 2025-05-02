package com.prueba.sponce.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String shippingAddress;
    private String notes;

    @OneToOne
    @JoinColumn(name = "order_id")
    private Order order;
}
