package com.project.ecommerce_youtube.request;

public class ProductRequest {
    private  Long id;

    private int price;

    private int quantity;

    public ProductRequest() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public ProductRequest(Long id, int price, int quantity) {
        this.id = id;
        this.price = price;
        this.quantity = quantity;
    }
}
