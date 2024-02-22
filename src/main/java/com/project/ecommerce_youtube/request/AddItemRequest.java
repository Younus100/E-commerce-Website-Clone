package com.project.ecommerce_youtube.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AddItemRequest {
    private Long productId;
    private String size;
    private int quantity;
    private Integer price;

}
