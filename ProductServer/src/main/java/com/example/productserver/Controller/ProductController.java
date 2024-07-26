package com.example.productserver.Controller;

import com.example.productserver.Dto.ProductDto;
import com.example.productserver.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController("/product")
public class ProductController {

    private final ProductService productService;

    public ProductController(@Autowired ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/create") //상품 등록
    public ResponseEntity<ProductDto> createProduct(@RequestBody ProductDto productDto) {
        ProductDto create = productService.createProduct(productDto);
        return ResponseEntity.ok().body(create);
    }

    @GetMapping("/{productId}") //상품 단건 조회
    public ResponseEntity<ProductDto> readProduct(@PathVariable Long productId) {
        ProductDto read = productService.readProduct(productId);
        return ResponseEntity.ok().body(read);
    }

    @PatchMapping("/update") //상품 수정
    public ResponseEntity<ProductDto> updateProduct(@RequestBody ProductDto productDto) {
        ProductDto update = productService.updateProduct(productDto);
        return ResponseEntity.ok().body(update);
    }

    @DeleteMapping("/{productId}") //상품 삭제
    public void deleteProduct(@PathVariable Long productId) {
        productService.deleteProduct(productId);
    }

}
