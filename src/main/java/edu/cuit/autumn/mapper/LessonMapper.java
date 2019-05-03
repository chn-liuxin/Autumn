package edu.cuit.autumn.mapper;

import edu.cuit.autumn.entity.Lesson;

import java.util.List;

import edu.cuit.autumn.entity.Subject;
import edu.cuit.autumn.entity.Teacher;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

@Repository
public interface LessonMapper {

    @Select("select * from lesson where teacher_id=#{teacherId}")
    List<Lesson> getLessonByTeacher(Teacher teacher);

    @Select("select * from lesson where subject_id=#{subjectId}")
    List<Lesson> getLessonBySubject(Subject subject);

    @Insert("insert into lesson values(#{lessonId},#{subjectId},#{teacherId},#{classId},#{subjectStartTime},#{subjectEndTime},#{lessonDay},#{lessonTime},#{lessonRoom})")
    void insertLesson(Lesson lesson);

    @Delete("delete from lesson where lesson_id=#{lessonId}")
    void deleteLesson(Lesson lesson);

    @Update("update lesson set subject_id=#{subjectId},teacher_id=#{teacherId},class_id=#{classId},subject_start_time=#{subjectStartTime},subject_end_time=#{subjectEndTime},lesson_day=#{lessonDay},lesson_time=#{lessonTime},lesson_room=#{lessonRoom} where lesson_id=#{lessonId}")
    void updateLesson(Lesson lesson);

}