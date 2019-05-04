package edu.cuit.autumn.service;

import edu.cuit.autumn.entity.ReviewTheory;

import java.util.List;

public interface ReviewTheoryService {

    List<ReviewTheory> getReviewTheoryByTeacherId(String teacherId);

    List<ReviewTheory> getReviewTheoryByReviewTeacherId(String reviewTeacherId);

    ReviewTheory getReviewTheoryById(String reviewId);

    void insertReviewTheory(ReviewTheory reviewTheory);

}
