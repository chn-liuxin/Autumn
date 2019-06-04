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
public class LessonServiceImpl implements LessonService {

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

    @Override
    public List<Lesson> getAllLesson() {
        return lessonMapper.getAllLesson();
    }

    @Override
    public List<Lesson> getLessonByIndex(int startIndex, int endIndex) {
        return lessonMapper.getLessonByIndex(startIndex, endIndex);
    }

    public Lesson getLessonByLessonId(String lessonId) {
        return lessonMapper.getLessonByLessonId(lessonId);
    }
}
