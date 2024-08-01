package com.example.productserver.Service;

import com.example.productserver.Dao.CategoryDao;
import com.example.productserver.Dao.ProductDao;
import com.example.productserver.Dto.ProductDto;
import com.example.productserver.Dto.ProductResponseDto;
import com.example.productserver.Entity.CategoryEntity;
import com.example.productserver.Entity.ProductEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService{

    private final ProductDao productDao;
    private final CategoryDao categoryDao;
    private final S3UploadUtil s3UploadUtil;

    public ProductServiceImpl(@Autowired ProductDao productDao,
                              CategoryDao categoryDao,
                              S3UploadUtil s3UploadUtil) {
        this.productDao = productDao;
        this.categoryDao = categoryDao;
        this.s3UploadUtil = s3UploadUtil;
    }

    @Override
    public ProductDto createProduct(ProductDto productDto, MultipartFile image) throws IOException {
        CategoryEntity categoryEntity = categoryDao.findByCategoryId(productDto.getCategoryId());

        String uploadUrl = null;

        if(!image.isEmpty()){
            File uploadFile = s3UploadUtil.convert(image) // MultipartFile 을 File 로 전환
                    .orElseThrow(() -> new IllegalArgumentException("MultipartFile -> File Convert Fail"));

            String PRODUCT_IMG_DIR = "product/";
            uploadUrl = s3UploadUtil.upload(uploadFile, PRODUCT_IMG_DIR);
        }

        ProductEntity productEntity = ProductDto.dtoToEntity(productDto, categoryEntity, uploadUrl);
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
    public ProductDto updateProduct(Long productId, ProductDto productDto) {
        return null;
    }

    @Override
    public void deleteProduct(Long productId) {
        ProductEntity productEntity = productDao.findById(productId);
        productDao.deleteProduct(productEntity);
    }
}
