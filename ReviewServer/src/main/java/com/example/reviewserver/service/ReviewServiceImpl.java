package com.example.reviewserver.service;

import com.example.reviewserver.dto.ReviewDto;
import com.example.reviewserver.dto.ReviewFeignDto;
import com.example.reviewserver.dto.ReviewResponseDto;
import com.example.reviewserver.entity.Review;
import com.example.reviewserver.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class ReviewServiceImpl implements ReviewService{

    private final ReviewRepository reviewRepository;
    private final S3UploadUtil s3UploadUtil;
    private final UserFeignClient userFeignClient;

    public ReviewServiceImpl(@Autowired ReviewRepository reviewRepository,
                             S3UploadUtil s3UploadUtil,
                             UserFeignClient userFeignClient) {
        this.reviewRepository = reviewRepository;
        this.s3UploadUtil = s3UploadUtil;
        this.userFeignClient = userFeignClient;
    }

    @Override
    @Transactional
    public ReviewDto save(ReviewDto reviewDto, MultipartFile image){

        URL uploadUrl = null;
        if(!image.isEmpty()){
            String REVIEW_IMG_DIR = "review/";
            uploadUrl = s3UploadUtil.fileUpload(image, REVIEW_IMG_DIR);
        }

        Review review = ReviewDto.dtoToEntity(reviewDto, String.valueOf(uploadUrl));
        reviewRepository.save(review);

        return ReviewDto.entityToDto(review);
    }

    @Override
    @Transactional
    public ReviewDto findReview(Long reviewId) {
        Review review = reviewRepository.findById(reviewId).get();
        return ReviewDto.entityToDto(review);
    }

    @Override
    @Transactional
    public void deleteReview(Long reviewId) {
        Review review = reviewRepository.findById(reviewId)
                        .orElseThrow(() -> new IllegalArgumentException("Review not found"));
        reviewRepository.delete(review);
    }

    @Override //추후 수정 필요. 간단하게 구현함
    @Transactional
    public ReviewDto updateReview(Long reviewId, ReviewDto reviewDto) {
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new IllegalArgumentException("Review not found"));
        if(reviewDto.getMemo() !=null)
            review.setMemo(reviewDto.getMemo());
        if(reviewDto.getStar() !=null)
            review.setStar(reviewDto.getStar());

        review.setCreatedDate(LocalDate.now());
        reviewRepository.save(review);
        return ReviewDto.entityToDto(review);
    }

    /**
     * 상품 리뷰 조회d
     */
    public List<ReviewDto> findProductReviews(Long productId){
        List<Review> reviews = reviewRepository.findByProductId(productId);
        return reviews.stream().
                map(ReviewDto::entityToDto)
                .collect(Collectors.toList());
    }

    /**
     * 유저가 작성한 리뷰 조회
     */
    public List<ReviewResponseDto> findUserReviews(Long userId){
        List<Review> reviews = reviewRepository.findByUserId(userId);
        return reviews.stream()
                .map(review -> ReviewResponseDto.entityToDto(review, userFeignClient.findById(review.getUserId())))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public ReviewFeignDto avgStarByProductReview(Long productId) {
        Float avgScore = reviewRepository.findAverageRatingByProductId(productId);
        return ReviewFeignDto.floatToDto(avgScore);
    }
}
