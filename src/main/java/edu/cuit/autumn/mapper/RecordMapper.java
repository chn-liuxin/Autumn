package edu.cuit.autumn.mapper;

import edu.cuit.autumn.entity.Record;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecordMapper {

    @Select("select * from record where record_id=#{recordId}")
    Record getRecordById(String recordId);

    @Select("select * from record where teacher_id=#{teacherId}")
    List<Record> getRecordByTeacherId(String teacherId);

    @Select("select * from record where review_teacher_id=#{reviewTeacherId}")
    List<Record> getRecordByReviewTeacherId(String reviewTeacherId);

    @Insert("insert into record values(#{recordId},#{type},#{teacherId},#{reviewTeacherId},#{lessonId},#{datetime},#{isHandle},#{status},#{reviewType},#{reviewId},#{isDelete})")
    void insertRecord(Record record);

    //录入评价
    @Update("update record set review_type=#{reviewType},review_id=#{reviewId} where record_id=#{recordId}")
    void entryReview(String recordId, String reviewType, String reviewId);

    //delete
    @Update("update record set is_delete=1 where record_id=#{recordId}")
    void toDelete(String recordId);

    //改变状态
    @Update("update record set status=${status},is_handle=1 where record_id=#{recordId}")
    void changeStatus(@Param("recordId") String recordId,@Param("status") byte status);

}