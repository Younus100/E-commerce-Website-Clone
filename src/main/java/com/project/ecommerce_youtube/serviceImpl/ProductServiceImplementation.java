package com.project.ecommerce_youtube.serviceImpl;

import com.project.ecommerce_youtube.Reository.CategoryRepository;
import com.project.ecommerce_youtube.Reository.ProductRepository;
import com.project.ecommerce_youtube.exception.ProductException;
import com.project.ecommerce_youtube.model.Category;
import com.project.ecommerce_youtube.model.Product;
import com.project.ecommerce_youtube.request.CreateProductRequest;
import com.project.ecommerce_youtube.service.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.QPageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class ProductServiceImplementation implements ProductService {

    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;

    public ProductServiceImplementation(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Product createProduct(CreateProductRequest req) {
        Category category =  categoryRepository.findByName(req.getTopLevelcategoty());
        if(category==null){
            Category LevelCategory = new Category();
            LevelCategory.setLevel(1);
            LevelCategory.setName(req.getTopLevelcategoty());

            category = categoryRepository.save(LevelCategory);
        }

        Category category2 =  categoryRepository.findByNameAndParent(req.getSecondLevelCategory(),category.getName());
        if(category2==null){
            Category LevelCategory = new Category();
            LevelCategory.setLevel(1);
            LevelCategory.setName(req.getTopLevelcategoty());

            category2 = categoryRepository.save(LevelCategory);
        }

        Category category3 =  categoryRepository.findByNameAndParent(req.getThirdLevelCategory(),category2.getName());
        if(category3==null){
            Category topLevelCategory = new Category();
            topLevelCategory.setLevel(1);
            topLevelCategory.setName(req.getTopLevelcategoty());

            category3 = categoryRepository.save(topLevelCategory);
        }

        Product product = new Product();
        product.setTitle(req.getTitle());
        product.setColor(req.getColor());
        product.setDescription(req.getDescription());
        product.setDiscountedPrice(req.getDiscounted_price());
        product.setImageUrl(req.getMage_url());
        product.setDiscountPresent(req.getDiscount_present());
        product.setBrand(req.getBrand());
        product.setPrice(req.getPrice());
        product.setQuantity(req.getQuantity());
        product.setSizes(req.getSizes());
        product.setCategory(category3);
        product.setCreatedAt(req.getCreatedAt());

        Product savesProduct = productRepository.save(product);

        return product;
    }

    @Override
    public String deleteProduct(Long productId) throws ProductException {
        Product product = productRepository.findProductById(productId);
        product.getSizes().clear();
        productRepository.delete(product);
        return "Product Deleted Successfully";
    }

    @Override
    public Product updateProduct(Long productId, Product req) throws ProductException {
        Product product = productRepository.findProductById(productId);
        if(req.getQuantity()!=0){
            product.setQuantity(req.getQuantity());
        }

        return productRepository.save(product);
    }

    @Override
    public Product getProductById(Long productId) throws ProductException {
        Product opt = productRepository.findProductById(productId);
        if(opt==null){  throw  new ProductException("Product not found with id"); }
        return opt;
    }

    @Override
    public List<Product> findproductByCategory(String categoty) throws ProductException {
        return null;
    }

    @Override
    public Page<Product> getAllProduct(String categoty, List<String> colors, List<String> sizes, Integer minPrice, Integer maxPrice,
                                       Integer minDiscount, String sort, String stock, Integer pageNumber, Integer pageSize) {

        Pageable pageable = PageRequest.of(pageNumber,pageSize);

        List<Product> products = productRepository.filterProducts(categoty,minPrice,maxPrice,minDiscount,sort);

        if(!colors.isEmpty()) {
            products= products.stream().filter(p->colors.stream().anyMatch(c->c.equalsIgnoreCase(p.getColor())))
                    .collect(Collectors.toList());
        }
        if(stock!=null){
            if(stock.equalsIgnoreCase("in_stock")){
                products=products.stream().filter(p->p.getQuantity()>0).collect(Collectors.toList());
            }else if(stock.equalsIgnoreCase("out_of_stock")){
                products=products.stream().filter(p->p.getQuantity()<1).collect(Collectors.toList());
            }
        }

        int startIndex = (int) pageable.getOffset();
        int endIndex = Math.min(startIndex+pageable.getPageNumber(),products.size());

        List<Product> pageContent = products.subList(startIndex,endIndex);

        Page<Product> fillteredProducts = new PageImpl<>(pageContent,pageable,products.size());
        return null;
    }
}
