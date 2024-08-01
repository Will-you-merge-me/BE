package com.example.productserver.Service;

import com.example.productserver.Dto.ProductDto;
import com.example.productserver.Dto.ProductResponseDto;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public interface ProductService {

    ProductDto createProduct(ProductDto productDto, MultipartFile image) throws IOException;

    ProductResponseDto readProduct(Long productId);

    ProductDto updateProduct(ProductDto productDto);

    void deleteProduct(Long productId);

    List<ProductDto> readAllByLargeCategory(String largeCategory);

    List<ProductDto> readAllBySmallCategory(String smallCategory);
}
