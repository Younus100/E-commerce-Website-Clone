package com.project.ecommerce_youtube.controller;


import com.project.ecommerce_youtube.exception.ProductException;
import com.project.ecommerce_youtube.model.Product;
import com.project.ecommerce_youtube.serviceImpl.ProductServiceImplementation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("pub/")
public class ProductController {

    ProductServiceImplementation productServiceImplementation;

    public ProductController(ProductServiceImplementation productServiceImplementation) {
        this.productServiceImplementation = productServiceImplementation;
    }

    @GetMapping("/product/category")
    public  List<Product> getProductById(@RequestParam("category") String name) throws ProductException {
        return productServiceImplementation.findproductByCategory(name);
    }
}
