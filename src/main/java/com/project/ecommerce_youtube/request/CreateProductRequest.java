package com.project.ecommerce_youtube.request;

import com.project.ecommerce_youtube.model.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
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
    private String topLevelCategory;
    private  String secondLevelCategory;
    private  String thirdLevelCategory;
    private  LocalDate createdAt;
    private  String description;
}

