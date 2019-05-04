package edu.cuit.autumn.service;

import edu.cuit.autumn.entity.ReviewExp;

import java.util.List;

public interface ReviewExpService {

    List<ReviewExp> getReviewExpByTeacherId(String teacherId);

    List<ReviewExp> getReviewExpByReviewTeacherId(String reviewTeacherId);

    ReviewExp getReviewExpById(String reviewId);

    void insertReviewTheory(ReviewExp reviewExp);

}
