package edu.cuit.autumn.service;

import edu.cuit.autumn.entity.Record;

import java.util.List;

public interface RecordService {

    Record getRecordById(String recordId);

    List<Record> getRecordByTeacherId(String teacherId);

    List<Record> getRecordByReviewTeacherId(String reviewTeacherId);

    void insertRecord(Record record);

    void updateRecordToComplete(String recordId);
}
