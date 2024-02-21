package com.project.ecommerce_youtube.Reository;

import com.project.ecommerce_youtube.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category,Long> {
    
    Category findByName(String name);

    @Query("SELECT c FROM Category c WHERE c.parentCategory = :parentCategory")
    List<Category> findAllWithParentCategory(@Param("parentCategory") Category parentCategory);

    @Query("SELECT c FROM Category c WHERE c.name = :name AND c.parentCategory.name = :parentCategoryName")
    Category findByNameAndParent(@Param("name")String  name,@Param("parentCategoryName")
            String parentCategoryName);
    
}
