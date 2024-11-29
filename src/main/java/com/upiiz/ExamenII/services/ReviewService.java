package com.upiiz.ExamenII.services;

import com.upiiz.ExamenII.entities.ReviewEntity;
import com.upiiz.ExamenII.repositories.ReviewRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewService {
    @Autowired
    ReviewRepository reviewRepository;

    public List<ReviewEntity> obtenerReviews() {
        return reviewRepository.findAll();
    }

    public ReviewEntity guardarReview(ReviewEntity review) {
        return reviewRepository.save(review);
    }

    public Optional<ReviewEntity> obtenerReviewPorId(Long id) {
        return Optional.ofNullable(reviewRepository.findReviewEntityById(id));
    }

    @Transactional
    public void deleteReview(Long id) {
        reviewRepository.deleteById(id);
    }

    public ReviewEntity actualizarReview(ReviewEntity review) {
        return reviewRepository.save(review);
    }
}
