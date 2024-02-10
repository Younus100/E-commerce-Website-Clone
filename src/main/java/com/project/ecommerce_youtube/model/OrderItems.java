package com.project.ecommerce_youtube.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDateTime;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public POrder getOrder() {
        return POrder;
    }

    public void setOrder(POrder POrder) {
        this.POrder = POrder;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getDiscountedPrice() {
        return discountedPrice;
    }

    public void setDiscountedPrice(Integer discountedPrice) {
        this.discountedPrice = discountedPrice;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Integer getTotalDiscountPrice() {
        return totalDiscountPrice;
    }

    public void setTotalDiscountPrice(Integer totalDiscountPrice) {
        this.totalDiscountPrice = totalDiscountPrice;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public LocalDateTime getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(LocalDateTime deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public OrderItems() {
    }

}
