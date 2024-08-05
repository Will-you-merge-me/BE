package com.example.productserver.Service;

import com.example.productserver.Dao.CategoryDao;
import com.example.productserver.Dao.ProductDao;
import com.example.productserver.Dto.ProductDto;
import com.example.productserver.Dto.ProductResponseDto;
import com.example.productserver.Dto.ReviewFeignDto;
import com.example.productserver.Dto.UserFeignDto;
import com.example.productserver.Entity.CategoryEntity;
import com.example.productserver.Entity.ProductEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.net.URL;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService{

    private final ProductDao productDao;
    private final CategoryDao categoryDao;
    private final S3UploadUtil s3UploadUtil;
    private final UserFeignClient userFeignClient;
    private final ReviewFeignClient reviewFeignClient;
    public ProductServiceImpl(@Autowired ProductDao productDao,
                              CategoryDao categoryDao,
                              S3UploadUtil s3UploadUtil,
                              UserFeignClient userFeignClient,
                              ReviewFeignClient reviewFeignClient) {
        this.productDao = productDao;
        this.categoryDao = categoryDao;
        this.s3UploadUtil = s3UploadUtil;
        this.userFeignClient = userFeignClient;
        this.reviewFeignClient = reviewFeignClient;
    }

    @Override
    public ProductDto createProduct(ProductDto productDto, MultipartFile image) {
        CategoryEntity categoryEntity = categoryDao.findByCategoryId(productDto.getCategoryId());

        URL uploadUrl = null;

        if(!image.isEmpty()){
            String PRODUCT_IMG_DIR = "product/";
            uploadUrl = s3UploadUtil.fileUpload(image, PRODUCT_IMG_DIR);
        }
        ProductEntity productEntity = ProductDto.dtoToEntity(productDto, categoryEntity, String.valueOf(uploadUrl));
        productEntity = productDao.createProduct(productEntity);
        return ProductDto.entityToDto(productEntity);
    }

    @Override
    public ProductResponseDto readProduct(Long productId) {
        ProductEntity productEntity = productDao.findById(productId);
        UserFeignDto userFeignDto = userFeignClient.findById(productEntity.getUserId());
        ReviewFeignDto reviewFeignDto = reviewFeignClient.avgStarByProductId(productId);
        return ProductResponseDto.entityToDto(productEntity, userFeignDto, reviewFeignDto);
    }

    @Override
    public List<ProductResponseDto> readAllByLargeCategory(String largeCategory) {
        List<ProductEntity> list = productDao.findByLargeCategory(largeCategory);
        return list.stream()
                .map(productEntity -> ProductResponseDto.entityToDto(productEntity, userFeignClient.findById(productEntity.getUserId()),
                        reviewFeignClient.avgStarByProductId(productEntity.getId())))
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductResponseDto> readAllBySmallCategory(String smallCategory) {
        List<ProductEntity> list = productDao.findBySmallCategory(smallCategory);
        return list.stream()
                .map(productEntity -> ProductResponseDto.entityToDto(productEntity, userFeignClient.findById(productEntity.getUserId()),
                        reviewFeignClient.avgStarByProductId(productEntity.getId())))
                .collect(Collectors.toList());
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
