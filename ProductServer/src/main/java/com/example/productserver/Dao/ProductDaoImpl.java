package com.example.productserver.Dao;

import com.example.productserver.Entity.ProductEntity;
import com.example.productserver.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ProductDaoImpl implements ProductDao{

    private final ProductRepository productRepository;

    public ProductDaoImpl(@Autowired ProductRepository productRepository) {
        this.productRepository  = productRepository;
    }
    @Override
    public void createProduct(ProductEntity productEntity) {
        productRepository.save(productEntity);
    }
}
