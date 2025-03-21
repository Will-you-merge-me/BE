package com.example.reviewserver.controller;

import com.example.reviewserver.dto.ReviewDto;
import com.example.reviewserver.dto.ReviewFeignDto;
import com.example.reviewserver.dto.ReviewResponseDto;
import com.example.reviewserver.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/review")
public class ReviewController {
    private final ReviewService reviewService;

    @Autowired
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    /**
     *리뷰 생성
     */
    @PostMapping("/create")
    public ResponseEntity<ReviewDto> createReview(@RequestPart(value = "reviewDto") ReviewDto reviewDto,
                                                  @RequestPart(value = "image", required = false) MultipartFile image){
        ReviewDto reviewDto1 = reviewService.save(reviewDto, image);
        return ResponseEntity.status(HttpStatus.CREATED).body(reviewDto1);
    }


    /**
     * 리뷰 삭제
     */
    @DeleteMapping("/{reviewId}")
    public void deleteReview(@PathVariable Long reviewId){
       reviewService.deleteReview(reviewId);
    }

    /**
     * 리뷰 단건 조회
     */
    @GetMapping("/{reviewId}")
    public ResponseEntity<ReviewDto> readReview(@PathVariable("reviewId") Long reviewId) {
        ReviewDto reviewDto = reviewService.findReview(reviewId);
        return ResponseEntity.ok().body(reviewDto);
    }
    /**
     * 상품 리뷰 조회
     */
    @GetMapping("/product/{productId}")
    public ResponseEntity<List<ReviewDto>> readReviewProduct(@PathVariable("productId") Long productId){
        List<ReviewDto> reviews = reviewService.findProductReviews(productId);
        return ResponseEntity.ok().body(reviews);
    }

    /**
     * 유저가 작성한 리뷰 조회
     */
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<ReviewResponseDto>> readReviewUser(@PathVariable("userId") Long userId){
        List<ReviewResponseDto> reviews = reviewService.findUserReviews(userId);
        return ResponseEntity.ok().body(reviews);
    }

    @PatchMapping("/{reviewId}")
    public ResponseEntity<ReviewDto> updateReview(@PathVariable Long reviewId, @RequestBody ReviewDto reviewDto){
        ReviewDto update = reviewService.updateReview(reviewId, reviewDto);
        return ResponseEntity.ok().body(update);
    }

    /**
     * 상품 평균 평점
     */
    @GetMapping("/feign/{productId}")
    public ReviewFeignDto avgStarByProductId(@PathVariable Long productId) {
        return reviewService.avgStarByProductReview(productId);
    }
}
