package com.project.ecommerce_youtube.request;

import com.project.ecommerce_youtube.model.Size;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class CreateProductRequest {
    private  String brand;
    private String color;
    private int  discount_present;
    private int discounted_price;
    private String mage_url;
    private int price;
    private int quantity;
    private String title;
    private Set<Size> sizes = new HashSet<>();
    private String topLevelcategoty;
    private  String secondLevelCategory;
    private  String thirdLevelCategory;

    private  LocalDate createdAt;

    private  String description;

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getDiscount_present() {
        return discount_present;
    }

    public void setDiscount_present(int discount_present) {
        this.discount_present = discount_present;
    }

    public int getDiscounted_price() {
        return discounted_price;
    }

    public void setDiscounted_price(int discounted_price) {
        this.discounted_price = discounted_price;
    }

    public String getMage_url() {
        return mage_url;
    }

    public void setMage_url(String mage_url) {
        this.mage_url = mage_url;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Set<Size> getSizes() {
        return sizes;
    }

    public void setSizes(Set<Size> sizes) {
        this.sizes = sizes;
    }

    public String getTopLevelcategoty() {
        return topLevelcategoty;
    }

    public void setTopLevelcategoty(String topLevelcategoty) {
        this.topLevelcategoty = topLevelcategoty;
    }

    public String getSecondLevelCategory() {
        return secondLevelCategory;
    }

    public void setSecondLevelCategory(String secondLevelCategory) {
        this.secondLevelCategory = secondLevelCategory;
    }

    public String getThirdLevelCategory() {
        return thirdLevelCategory;
    }

    public void setThirdLevelCategory(String thirdLevelCategory) {
        this.thirdLevelCategory = thirdLevelCategory;
    }

    public String getDescription() {
            return description;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }
}
