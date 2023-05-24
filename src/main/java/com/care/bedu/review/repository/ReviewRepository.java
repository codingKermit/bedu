package com.care.bedu.review.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.care.bedu.review.entity.Review;

public interface ReviewRepository extends JpaRepository<Review, Integer> {
}