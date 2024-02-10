package com.project.ecommerce_youtube.model;

public class Size {
    private int quantity;
    private String name;

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

   Size() { }

    public Size(int quantity, String name) {
        this.quantity = quantity;
        this.name = name;
    }
}
