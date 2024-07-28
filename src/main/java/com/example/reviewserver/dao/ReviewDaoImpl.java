package com.example.reviewserver.dao;

import com.example.reviewserver.entity.Review;
import com.example.reviewserver.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ReviewDaoImpl implements ReviewDao{

    private final ReviewRepository reviewRepository;

    @Override
    public Long save(Review review) {
        return reviewRepository.save(review).getId();
    }

    public void delete(Long findId) {
        reviewRepository.deleteById(findId);
    }
}
