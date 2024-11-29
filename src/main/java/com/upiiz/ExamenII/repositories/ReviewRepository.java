package com.upiiz.ExamenII.repositories;

import com.upiiz.ExamenII.entities.ReviewEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<ReviewEntity, Long> {

    @Query("SELECT r FROM ReviewEntity r WHERE r.id = :id")
    ReviewEntity findReviewEntityById(@Param("id") Long id);

}
