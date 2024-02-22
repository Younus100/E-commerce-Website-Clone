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
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
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
        Category category =  categoryRepository.findByName(req.getTopLevelCategory());
        if(category==null){
            Category LevelCategory = new Category();
            LevelCategory.setLevel(1);
            LevelCategory.setName(req.getTopLevelCategory());

            category = categoryRepository.save(LevelCategory);
        }

        Category category2 = categoryRepository.findByNameAndParent(req.getSecondLevelCategory(), category.getName());
        if (category2 == null) {
            Category secondLevelCategory = new Category();
            secondLevelCategory.setLevel(2);
            secondLevelCategory.setName(req.getSecondLevelCategory());
            secondLevelCategory.setParentCategory(category);// Use getSecondLevelCategory()

            category2 = categoryRepository.save(secondLevelCategory);
        }

        Category category3 = categoryRepository.findByNameAndParent(req.getThirdLevelCategory(), category2.getName());
        if (category3 == null) {
            Category thirdLevelCategory = new Category();
            thirdLevelCategory.setLevel(3);
            thirdLevelCategory.setName(req.getThirdLevelCategory()); // Use getThirdLevelCategory()
            thirdLevelCategory.setParentCategory(category2);// Use getSecondLevelCategory()

            category3 = categoryRepository.save(thirdLevelCategory);
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

        product.setCreatedAt(LocalDate.now());

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
    public List<Product> findproductByCategory(String category) throws ProductException {
        // Retrieve the category from the repository
        Category categoryEntity = categoryRepository.findByName(category);
        List<Category> categories = new ArrayList<>();
        List<Category> full = new ArrayList<>();
        if (categoryEntity != null) {
            if(categoryEntity.getLevel()==1) {
                categories = categoryRepository.findAllWithParentCategory(categoryEntity);
                for(Category c : categories){
                    List<Category> temp = new ArrayList<>();
                    temp = categoryRepository.findAllWithParentCategory(c);
                    full.addAll(temp);
                }
            } else if(categoryEntity.getLevel()==2) {
                full = categoryRepository.findAllWithParentCategory(categoryEntity);
            }

            List<Product> products = new ArrayList<>();
            // If the category exists, query for products with the specified category
            for(Category c:full){
                List<Product> temp =  productRepository.findByCategory(c);
                products.addAll(temp);
            }
            return products;
        } else {
            // Handle the case where the category does not exist
            throw new ProductException("Category not found: " + category);
        }
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

    @Override
    @Transactional
    public void updateProductP(Product product) {
        productRepository.save(product);
    }
}
