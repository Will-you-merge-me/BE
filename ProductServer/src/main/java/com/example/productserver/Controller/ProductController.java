package com.example.productserver.Controller;

import com.example.productserver.Dto.ProductDto;
import com.example.productserver.Dto.ProductResponseDto;
import com.example.productserver.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;

    public ProductController(@Autowired ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/create") //상품 등록
    public ResponseEntity<ProductDto> createProduct(@RequestBody ProductDto productDto,
                                                    @RequestPart(value = "image", required = false)MultipartFile image) throws IOException {
        ProductDto create = productService.createProduct(productDto, image);
        return ResponseEntity.ok().body(create);
    }

    @GetMapping("/{productId}") //상품 단건 조회
    public ResponseEntity<ProductResponseDto> readProduct(@PathVariable Long productId) {
        ProductResponseDto read = productService.readProduct(productId);
        return ResponseEntity.ok().body(read);
    }

    @GetMapping("/large/{largeCategory}") //대분류 카테고리별 상품 전체보기
    public ResponseEntity<List<ProductDto>> readAllByLargeCategory(@PathVariable("largeCategory") String largeCategory) {
        List<ProductDto> productDtoList = productService.readAllByLargeCategory(largeCategory);
        return ResponseEntity.ok().body(productDtoList);
    }

    @GetMapping("/small/{smallCategory}") //소분류 카테고리별 상품 전체보기
    public ResponseEntity<List<ProductDto>> readAllBySmallCategory(@PathVariable("smallCategory") String smallCategory) {
        List<ProductDto> productDtoList = productService.readAllBySmallCategory(smallCategory);
        return ResponseEntity.ok().body(productDtoList);
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
