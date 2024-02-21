package com.project.ecommerce_youtube.controller.admin;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.ecommerce_youtube.request.CreateProductRequest;
import com.project.ecommerce_youtube.service.ProductService;
import com.project.ecommerce_youtube.serviceImpl.admin.FirebaseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/pub")
public class ImageController {

    FirebaseService firebaseService;

    ObjectMapper objectMapper;

    ProductService productService;

    public ImageController(FirebaseService firebaseService, ObjectMapper objectMapper, ProductService productService) {
        this.firebaseService = firebaseService;
        this.objectMapper = objectMapper;
        this.productService = productService;
    }

    @PostMapping("/addProduct")
    public ResponseEntity<String> addProduct( @RequestParam("json") String request, @RequestParam("file") MultipartFile file) {
        try {
                //Converting String into Json
            CreateProductRequest createProductRequest=objectMapper.readValue(request,CreateProductRequest.class);
//             Upload the image to Firebase and get the image URL
            String imageUrl = firebaseService.uploadImage(file);
            System.out.println(imageUrl);
            createProductRequest.setMage_url(imageUrl);
            if(createProductRequest.getBrand().length()!=0){
                productService.createProduct(createProductRequest);
                System.out.println("Pass");
            }
//             Save the imageUrl along with other product details
            return ResponseEntity.ok("Product added successfully.");
        } catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to add product.");
        }
    }

    @GetMapping("/v1")
    public String test(){
        return "Test";
    }
}
