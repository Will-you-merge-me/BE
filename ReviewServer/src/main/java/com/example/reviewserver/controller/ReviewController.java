package com.example.reviewserver.controller;

import com.example.reviewserver.dto.ReviewDto;
import com.example.reviewserver.entity.Review;
import com.example.reviewserver.service.ReviewService;
import com.example.reviewserver.service.ReviewServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;


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
    public ResponseEntity<ReviewDto> createReview(@RequestBody ReviewDto reviewDto) throws IOException {
        ReviewDto reviewDto1 = reviewService.save(reviewDto);
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
    public ResponseEntity<List<ReviewDto>> readReviewUser(@PathVariable("userId") Long userId){
        List<ReviewDto> reviews = reviewService.findUserReviews(userId);
        return ResponseEntity.ok().body(reviews);
    }

    @PatchMapping("/{reviewId}")
    public ResponseEntity<ReviewDto> updateReview(@PathVariable("reviewId") Long reviewId,
                                                  @RequestBody ReviewDto reviewDto) {
        ReviewDto updateReview = reviewService.updateReview(reviewId, reviewDto);
        return ResponseEntity.ok().body(updateReview);
    }
}
