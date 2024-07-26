package com.example.productserver.Service;

import com.example.productserver.Dto.ProductDto;
import org.springframework.stereotype.Service;

@Service
public interface ProductService {

    ProductDto createProduct(ProductDto productDto);

    ProductDto readProduct(Long productId);

    ProductDto updateProduct(ProductDto productDto);

    void deleteProduct(Long productId);
}
