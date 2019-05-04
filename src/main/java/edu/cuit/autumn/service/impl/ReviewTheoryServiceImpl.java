package edu.cuit.autumn.service.impl;

import edu.cuit.autumn.entity.ReviewTheory;
import edu.cuit.autumn.mapper.ReviewTheoryMapper;
import edu.cuit.autumn.service.ReviewTheoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewTheoryServiceImpl implements ReviewTheoryService {

    @Autowired
    ReviewTheoryMapper reviewTheoryMapper;

    @Override
    public List<ReviewTheory> getReviewTheoryByTeacherId(String teacherId) {
        return reviewTheoryMapper.getReviewTheoryByTeacherId(teacherId);
    }

    @Override
    public List<ReviewTheory> getReviewTheoryByReviewTeacherId(String reviewTeacherId) {
        return reviewTheoryMapper.getReviewTheoryByReviewTeacherId(reviewTeacherId);
    }

    @Override
    public ReviewTheory getReviewTheoryById(String reviewId) {
        return reviewTheoryMapper.getReviewTheoryById(reviewId);
    }

    @Override
    public void insertReviewTheory(ReviewTheory reviewTheory) {
        reviewTheoryMapper.insertReviewTheory(reviewTheory);
    }
}
