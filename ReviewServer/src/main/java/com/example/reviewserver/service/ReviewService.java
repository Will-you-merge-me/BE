package com.example.reviewserver.service;

import com.example.reviewserver.dto.ReviewDto;
import com.example.reviewserver.entity.Review;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface ReviewService {
    ReviewDto save(ReviewDto reviewDto) throws IOException;
    List<ReviewDto> findProductReviews(Long productId);
    List<ReviewDto> findUserReviews(Long userId);
    ReviewDto updateReview(Long reviewId, ReviewDto reviewDto);
    void deleteReview(Long reviewId);
}
