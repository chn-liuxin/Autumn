package edu.cuit.autumn.mapper;

import edu.cuit.autumn.entity.Subject;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubjectMapper {

    @Select("select * from subject")
    List<Subject> getAllSubject();

    @Select("select * from subject where subject_type=#{subjectType}")
    List<Subject> getSubjectByType(String subjectType);

    @Select("select * from subject where subject_id=#{subjectId}")
    Subject getSubjectById(String subjectId);

    @Insert("insert into subject values(#{subjectId},#{subjectName},#{subjectCredit},#{subjectHour},#{subjectType},#{subjectStartTime},#{subjectEndTime})")
    void insertSubject(Subject subject);

    @Delete("delete from subject where subject_id=#{subjectId}")
    void deleteSubject(Subject subject);

    @Update("update subject set subject_name=#{subjectName},subject_credit=#{subjectCredit},subject_hour=#{subjectHour},subject_type=#{subjectType},subject_start_time=#{subjectStartTime},subject_end_time=#{subjectEndTime} where subject_id=#{subjectId}")
    void updateSubject(Subject subject);

    @Select("select * from subject where subject_name like #{subjectName}")
    List<Subject> getSubjectFuzzy(String subjectName);

    @Select("select * from subject where subject_name=#{subjectname}")
    Subject getSubjectByName(String subjectName);

}