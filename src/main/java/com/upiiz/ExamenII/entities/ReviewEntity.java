package com.upiiz.ExamenII.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "review")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ReviewEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "product_id", nullable = false)
    private Long product_id;

    @Column(name = "rewview_text")
    private String review_text;

    @Min(1)
    @Max(5)
    @Column(name = "rating")
    private int rating;

    @Column(name = "review_date")
    private LocalDateTime review_date;

    @Column(name = "usr")
    private String user;

}
