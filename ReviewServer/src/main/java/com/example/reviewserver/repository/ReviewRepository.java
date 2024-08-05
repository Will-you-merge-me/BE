package com.example.reviewserver.repository;

import com.example.reviewserver.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByProductId(Long id);
    List<Review> findByUserId(Long id);

    @Query("SELECT AVG(r.star) FROM Review r WHERE r.productId = :productId")
    Float findAverageRatingByProductId(@Param("productId") Long productId);
}
