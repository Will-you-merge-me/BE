package com.example.productserver.Repository;

import com.example.productserver.Entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity,Long> {
    List<ProductEntity> findByCategoryEntityLargeCategory(String largeCategory);

    List<ProductEntity> findByCategoryEntitySmallCategory(String smallCategory);
}
