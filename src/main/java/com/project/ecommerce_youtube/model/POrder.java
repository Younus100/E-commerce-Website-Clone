package com.project.ecommerce_youtube.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class POrder {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private  Long id;

    private String orderId;

    @ManyToOne
    @JoinColumn()
    private User user;

    @OneToMany(mappedBy = "POrder", cascade = CascadeType.ALL)
    private List<OrderItems> orderItemsList = new ArrayList<>();


    private LocalDateTime orderDate;

    private LocalDateTime deliveryDate;

    @OneToOne
    private Address shippingAddress;

    @Embedded
    private PaymentDetails paymentDetails = new PaymentDetails();

    private double totalPrice;

    private Integer totalDiscountPrice;

    private String orderStatus;

    private int totalItem;

    private LocalDateTime createdAt;

}
