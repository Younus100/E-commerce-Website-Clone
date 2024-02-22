package com.project.ecommerce_youtube.service;

import com.project.ecommerce_youtube.exception.ProductException;
import com.project.ecommerce_youtube.model.Product;
import com.project.ecommerce_youtube.request.CreateProductRequest;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductService {
    public Product createProduct(CreateProductRequest req);

    public String deleteProduct(Long productId) throws ProductException;
    
    Product updateProduct(Long productId, Product req) throws ProductException;

    public Product getProductById(Long productId) throws ProductException;

    public List<Product> findproductByCategory(String categoty) throws ProductException;

    Page<Product> getAllProduct(String categoty, List<String> colors, List<String> sizes, Integer minPrice, Integer maxPrice,
                                Integer minDiscount, String sort, String stock, Integer pageNumber, Integer pageSize);

    public void updateProductP(Product product);

}
