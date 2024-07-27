package com.example.productserver.Service;

import com.example.productserver.Dao.CategoryDao;
import com.example.productserver.Dao.ProductDao;
import com.example.productserver.Dto.ProductDto;
import com.example.productserver.Entity.CategoryEntity;
import com.example.productserver.Entity.ProductEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService{

    private final ProductDao productDao;
    private final CategoryDao categoryDao;


    public ProductServiceImpl(@Autowired ProductDao productDao,
                              CategoryDao categoryDao) {
        this.productDao = productDao;
        this.categoryDao = categoryDao;
    }

    @Override
    public ProductDto createProduct(ProductDto productDto) {
        CategoryEntity categoryEntity = categoryDao.findByCategoryId(productDto.getCategoryId());
        ProductEntity productEntity = ProductDto.dtoToEntity(productDto, categoryEntity);
        productDao.createProduct(productEntity);
        return ProductDto.entityToDto(productEntity);
    }

    @Override
    public ProductDto readProduct(Long productId) {
        return null;
    }

    @Override
    public List<ProductDto> readAllByLargeCategory(String largeCategory) {
        return List.of();
    }

    @Override
    public List<ProductDto> readAllBySmallCategory(String smallCategory) {
        return List.of();
    }

    @Override
    public ProductDto updateProduct(ProductDto productDto) {
        return null;
    }

    @Override
    public void deleteProduct(Long productId) {

    }
}
