package edu.cuit.autumn.mapper;

import edu.cuit.autumn.entity.Subject;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

@Repository
public interface SubjectMapper {

    @Select("select * from subject")
    List<Subject> getAllSubject();

    @Select("select * from subject where subject_type=#{subjectType}")
    List<Subject> getSubjectByType(String subjectType);

    @Insert("insert into subject values(#{subjectId},#{subjectName},#{subjectCredit},#{subjectHour},#{subjectType},#{subjectStartTime},#{subjectEndTime})")
    void insertSubject(Subject subject);

    @Delete("delete from subject where subject_id=#{subjectId}")
    void deleteSubject(Subject subject);

    @Update("update subject set subject_name=#{subjectName},subject_credit=#{subjectCredit},subject_hour=#{subjectHour},subject_type=#{subjectType},subject_start_time=#{subjectStartTime},subject_end_time=#{subjectEndTime} where subject_id=#{subjectId}")
    void updateSubject(Subject subject);

}