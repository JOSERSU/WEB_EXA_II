package com.upiiz.ExamenII.controllers;

import com.upiiz.ExamenII.entities.ReviewEntity;
import com.upiiz.ExamenII.services.ReviewService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/reviews")
@Tag(
        name = "Reviews"
)
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    //GET TODOS
    @PreAuthorize("hasAuthority('READ')")
    @GetMapping
    public ResponseEntity<List<ReviewEntity>> getReview(){
        return ResponseEntity.ok(reviewService.obtenerReviews());
    }
    //GET UNO
    @GetMapping("/{id}")
    public ResponseEntity<Optional<ReviewEntity>> getReviewById(@PathVariable Long id){
        return ResponseEntity.ok(reviewService.obtenerReviewPorId(id));
    }
    //POST
    //@PreAuthorize("hasAuthority('CREATE')")
    @PostMapping
    public ResponseEntity<ReviewEntity> createReview(@RequestBody ReviewEntity review){
        return ResponseEntity.ok(reviewService.guardarReview(review));
    }
    //PUT
    @PutMapping("/{id}")
    public ResponseEntity<ReviewEntity> updateReview(@RequestBody ReviewEntity review, @PathVariable Long id){
        review.setId(id);
        return ResponseEntity.ok(reviewService.actualizarReview(review));
    }
    //DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<ReviewEntity> deleteReview(@PathVariable Long id){
        reviewService.deleteReview(id);
        return ResponseEntity.noContent().build();
    }


}
