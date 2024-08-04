package com.example.reviewserver.service;

import com.example.reviewserver.dto.ReviewDto;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;

public interface ReviewService {
    ReviewDto save(ReviewDto reviewDto, MultipartFile image);
    List<ReviewDto> findProductReviews(Long productId);
    List<ReviewDto> findUserReviews(Long userId);
    void deleteReview(Long reviewId);

    ReviewDto updateReview(Long reviewId, ReviewDto reviewDto);
}
