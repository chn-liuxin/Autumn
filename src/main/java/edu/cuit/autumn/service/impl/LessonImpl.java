package edu.cuit.autumn.service.impl;

import edu.cuit.autumn.entity.Lesson;
import edu.cuit.autumn.entity.Subject;
import edu.cuit.autumn.entity.Teacher;
import edu.cuit.autumn.mapper.LessonMapper;
import edu.cuit.autumn.service.LessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LessonImpl implements LessonService {

    @Autowired
    LessonMapper lessonMapper;

    @Override
    public List<Lesson> getLessonByTeacher(Teacher teacher) {
        return lessonMapper.getLessonByTeacher(teacher);
    }

    @Override
    public List<Lesson> getLessonBySubject(Subject subject) {
        return lessonMapper.getLessonBySubject(subject);
    }

    @Override
    public void insertLesson(Lesson lesson) {
        lessonMapper.insertLesson(lesson);
    }

    @Override
    public void deleteLesson(Lesson lesson) {
        lessonMapper.deleteLesson(lesson);
    }

    @Override
    public void updateLesson(Lesson lesson) {
        lessonMapper.updateLesson(lesson);
    }

}
