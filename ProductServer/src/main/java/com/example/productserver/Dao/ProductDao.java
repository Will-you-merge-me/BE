package com.example.productserver.Dao;

import com.example.productserver.Entity.ProductEntity;

import java.util.List;

public interface ProductDao {
    ProductEntity createProduct(ProductEntity productEntity);

    ProductEntity findById(Long productId);

    List<ProductEntity> findByLargeCategory(String largeCategory);

    List<ProductEntity> findBySmallCategory(String smallCategory);

    void deleteProduct(ProductEntity productEntity);
}
