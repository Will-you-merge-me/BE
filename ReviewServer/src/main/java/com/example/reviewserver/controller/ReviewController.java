package com.example.reviewserver.controller;

import com.example.reviewserver.dto.ReviewDto;
import com.example.reviewserver.entity.Review;
import com.example.reviewserver.repository.ReviewRepository;
import com.example.reviewserver.service.ReviewServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewRepository reviewRepository;
    private final ReviewServiceImpl reviewService;

    /**
     *리뷰 생성
     */
    @PostMapping("/review}")
    ResponseEntity<ReviewDto> createReview(@RequestBody ReviewDto reviewDto){
        Review review = new Review();
        review.setReview(reviewDto);
        reviewService.save(review);
        return ResponseEntity.ok(reviewDto);
    }


    /**
     * 리뷰 삭제
     */
    @DeleteMapping("/review")
    ResponseEntity<String> deleteReview(@RequestBody ReviewDto reviewDto){
        Review findReview = reviewService.findById(reviewDto.getId());
        if(findReview != null){
            Long findId = findReview.getId();
            reviewService.delete(findId);
            return ResponseEntity.ok("삭제성공");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("찾지 못함");
    }

    /**
     * 사진 리뷰 조회
     */
    @GetMapping("/photo-review/product/{productId}")
    ResponseEntity<List<ReviewDto>> printReviewWithPhoto(@PathVariable("productId") Long productId){
        List<Review> photoReviews = reviewService.findPhotoReviews(productId);
        List<ReviewDto> result = photoReviews.stream().map(goal -> new ReviewDto(goal)).collect(Collectors.toList());
        return ResponseEntity.ok(result);
    }

    /**
     * 일반 리뷰 조회
     */
    @GetMapping("/review/product/{productId}")
    ResponseEntity<List<ReviewDto>> printReview(@PathVariable("productId") Long productId){
        List<Review> reviews = reviewService.findReviews(productId);
        List<ReviewDto> result = reviews.stream().map(goal -> new ReviewDto(goal)).collect(Collectors.toList());
        return ResponseEntity.ok(result);
    }


}
