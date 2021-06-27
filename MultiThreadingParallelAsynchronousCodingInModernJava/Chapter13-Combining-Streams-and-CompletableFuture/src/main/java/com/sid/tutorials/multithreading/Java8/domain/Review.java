package com.sid.tutorials.multithreading.Java8.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Review {
    private int noOfReviews;
    private double overallRating;
}
