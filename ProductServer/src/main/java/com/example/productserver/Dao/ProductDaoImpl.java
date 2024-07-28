package com.example.productserver.Dao;

import com.example.productserver.Entity.ProductEntity;
import com.example.productserver.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductDaoImpl implements ProductDao{

    private final ProductRepository productRepository;

    public ProductDaoImpl(@Autowired ProductRepository productRepository) {
        this.productRepository  = productRepository;
    }
    @Override
    public ProductEntity createProduct(ProductEntity productEntity) {
        return productRepository.save(productEntity);
    }

    @Override
    public ProductEntity findById(Long productId) {
        return productRepository.findById(productId).
                orElseThrow(() -> new IllegalArgumentException("상품을 찾을 수 없습니다."));
    }

    @Override
    public List<ProductEntity> findByLargeCategory(String largeCategory) {
        return productRepository.findByCategoryEntityLargeCategory(largeCategory);
    }

    @Override
    public List<ProductEntity> findBySmallCategory(String smallCategory) {
        return productRepository.findByCategoryEntitySmallCategory(smallCategory);
    }

    @Override
    public void deleteProduct(ProductEntity productEntity) {
        productRepository.delete(productEntity);
    }
}
