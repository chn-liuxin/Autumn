package edu.cuit.autumn.mapper;

import edu.cuit.autumn.entity.Record;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

@Repository
public interface RecordMapper {

    @Select("select * from record where record_id=#{recordId}")
    Record getRecordById(String recordId);

    @Select("select * from record where teacher_id=#{teacherId}")
    List<Record> getRecordByTeacherId(String teacherId);

    @Select("select * from record where review_teacher_id=#{reviewTeacherId}")
    List<Record> getRecordByReviewTeacherId(String reviewTeacherId);

    @Insert("insert into record values(#{recordId},#{type},#{teacherId},#{reviewTeacherId},#{lessonId},#{status},#{isComplete})")
    void insertRecord(Record record);

    @Update("update record set isComplete=true where record_id=#{recordId}")
    void updateRecordToComplete(String recordId);
}