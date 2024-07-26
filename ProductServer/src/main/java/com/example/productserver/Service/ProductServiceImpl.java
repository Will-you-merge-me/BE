package com.example.productserver.Service;

import com.example.productserver.Dao.ProductDao;
import com.example.productserver.Dto.ProductDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService{

    private final ProductDao productDao;

    @Autowired
    public ProductServiceImpl(@Autowired ProductDao productDao) {
        this.productDao = productDao;
    }

    @Override
    public ProductDto createProduct(ProductDto productDto) {
        return null;
    }

    @Override
    public ProductDto readProduct(Long productId) {
        return null;
    }

    @Override
    public ProductDto updateProduct(ProductDto productDto) {
        return null;
    }

    @Override
    public void deleteProduct(Long productId) {

    }
}
