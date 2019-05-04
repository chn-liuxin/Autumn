package edu.cuit.autumn.service.impl;

import edu.cuit.autumn.entity.ReviewExp;
import edu.cuit.autumn.mapper.ReviewExpMapper;
import edu.cuit.autumn.service.ReviewExpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewExpServiceImpl implements ReviewExpService {

    @Autowired
    ReviewExpMapper reviewExpMapper;

    @Override
    public List<ReviewExp> getReviewExpByTeacherId(String teacherId) {
        return reviewExpMapper.getReviewExpByTeacherId(teacherId);
    }

    @Override
    public List<ReviewExp> getReviewExpByReviewTeacherId(String reviewTeacherId) {
        return reviewExpMapper.getReviewExpByReviewTeacherId(reviewTeacherId);
    }

    @Override
    public ReviewExp getReviewExpById(String reviewId) {
        return reviewExpMapper.getReviewExpById(reviewId);
    }

    @Override
    public void insertReviewTheory(ReviewExp reviewExp) {
        reviewExpMapper.insertReviewTheory(reviewExp);
    }

}
