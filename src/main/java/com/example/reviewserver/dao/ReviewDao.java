package com.example.reviewserver.dao;

import com.example.reviewserver.entity.Review;

public interface ReviewDao {
    Long save(Review review);
}
