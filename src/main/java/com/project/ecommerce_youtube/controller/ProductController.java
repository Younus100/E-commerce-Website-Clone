package com.project.ecommerce_youtube.controller;


import com.project.ecommerce_youtube.exception.ProductException;
import com.project.ecommerce_youtube.model.Product;
import com.project.ecommerce_youtube.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/{productId}")
    public ResponseEntity<Product> getProductById(@PathVariable Long productId) {
        try {
            Product product = productService.getProductById(productId);
            return ResponseEntity.ok(product);
        } catch (ProductException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
}
//package com.project.ecommerce_youtube.controller;
//
//import com.project.ecommerce_youtube.model.Product;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//@RequestMapping("/product")
//public class ProductController {
//
//    @GetMapping("/product/{productid}")
//    Product getProductById()
//}
