package com.example.reviewserver.service;

import com.example.reviewserver.entity.Review;

import java.util.List;
import java.util.Optional;

public interface ReviewService {
    Long save(Review review);

    List<Review> findAll();

    Review findById(Long id);
}
