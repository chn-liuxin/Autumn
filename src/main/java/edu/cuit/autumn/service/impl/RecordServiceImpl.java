package edu.cuit.autumn.service.impl;

import edu.cuit.autumn.entity.Record;
import edu.cuit.autumn.mapper.RecordMapper;
import edu.cuit.autumn.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecordServiceImpl implements RecordService {

    @Autowired
    RecordMapper recordMapper;

    @Override
    public List<Record> getRecordByTeacherId(String teacherId) {
        return recordMapper.getRecordByTeacherId(teacherId);
    }

    @Override
    public List<Record> getRecordByReviewTeacherId(String reviewTeacherId) {
        return recordMapper.getRecordByReviewTeacherId(reviewTeacherId);
    }

    @Override
    public void entryReview(String recordId, String reviewType, String reviewId) {
        recordMapper.entryReview(recordId,reviewType,reviewId);
    }

    @Override
    public void toDelete(String recordId) {
        recordMapper.toDelete(recordId);
    }

    @Override
    public void changeStatus(String recordId, byte status) {
        recordMapper.changeStatus(recordId,status);
    }

    @Override
    public Record getRecordById(String recordId) {
        return recordMapper.getRecordById(recordId);
    }

    @Override
    public void insertRecord(Record record) {
        recordMapper.insertRecord(record);
    }
}
