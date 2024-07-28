package com.example.reviewserver.service;

import com.example.reviewserver.dao.ReviewDaoImpl;
import com.example.reviewserver.entity.Review;
import com.example.reviewserver.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReviewServiceImpl implements ReviewService{

    private final ReviewDaoImpl reviewDao;
    private final ReviewRepository reviewRepository;

    @Override
    @Transactional
    public Long save(Review review) {
        return reviewDao.save(review);
    }

    @Override
    public List<Review> findAll() {
        return reviewRepository.findAll();
    }

    @Override
    public Review findById(Long id) {
        return reviewRepository.findById(id).get();
    }

    public void delete(Long findId) {
        reviewDao.delete(findId);
    }

    /**
     * 포토리뷰 조회
     */
    public List<Review> findPhotoReviews(Long productId){
        return reviewRepository.findAllByProductIdAndPictureIsNotNull(productId);
    }

    /**
     * 일반리뷰 조회
     */
    public List<Review> findReviews(Long productId){
        return reviewRepository.findAllByProductIdAndPictureIsNull(productId);
    }
}
