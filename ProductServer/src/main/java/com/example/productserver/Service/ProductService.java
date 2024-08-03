package com.example.productserver.Service;

import com.example.productserver.Dto.ProductDto;
import com.example.productserver.Dto.ProductResponseDto;
import org.springframework.web.multipart.MultipartFile;


import java.util.List;

public interface ProductService {

    ProductDto createProduct(ProductDto productDto, MultipartFile image);

    ProductResponseDto readProduct(Long productId);

    ProductDto updateProduct(Long productId, ProductDto productDto);

    void deleteProduct(Long productId);

    List<ProductResponseDto> readAllByLargeCategory(String largeCategory);

    List<ProductResponseDto> readAllBySmallCategory(String smallCategory);
}
