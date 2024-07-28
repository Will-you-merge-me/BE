package com.example.productserver.Service;

import com.example.productserver.Dao.CategoryDao;
import com.example.productserver.Dao.ProductDao;
import com.example.productserver.Dto.ProductDto;
import com.example.productserver.Dto.ProductResponseDto;
import com.example.productserver.Entity.CategoryEntity;
import com.example.productserver.Entity.ProductEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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
        productEntity = productDao.createProduct(productEntity);
        return ProductDto.entityToDto(productEntity);
    }

    @Override
    public ProductResponseDto readProduct(Long productId) {
        ProductEntity productEntity = productDao.findById(productId);
        //TODO : 유저네임 넣는 openfeign 필요
        return ProductResponseDto.entityToDto(productEntity,"테스트케이스");
    }

    @Override
    public List<ProductDto> readAllByLargeCategory(String largeCategory) {
        List<ProductEntity> list = productDao.findByLargeCategory(largeCategory);
        return list.stream().
                map(ProductDto::entityToDto).
                collect(Collectors.toList());
    }

    @Override
    public List<ProductDto> readAllBySmallCategory(String smallCategory) {
        List<ProductEntity> list = productDao.findBySmallCategory(smallCategory);
        return list.stream().
                map(ProductDto::entityToDto).
                collect(Collectors.toList());
    }

    @Override
    public ProductDto updateProduct(ProductDto productDto) {
        return null;
    }

    @Override
    public void deleteProduct(Long productId) {
        ProductEntity productEntity = productDao.findById(productId);
        productDao.deleteProduct(productEntity);
    }
}
