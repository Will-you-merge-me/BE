package com.example.productserver.Repository;

import com.example.productserver.Entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity,Long> {
    CategoryEntity findBySmallCategory(String smallCategory);
}
