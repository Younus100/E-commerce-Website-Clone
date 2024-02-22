package com.project.ecommerce_youtube.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Entity
public class OrderItems {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private  Long id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn( name = "porder_id")
    private POrder POrder;

    @ManyToOne
    private Product product;


    private String  size;

    private int quantity;

    private Integer price;

    private Integer discountedPrice ;

    private double totalPrice;

    private Integer totalDiscountPrice;

    private Long userId;

    private LocalDateTime deliveryDate;

}
