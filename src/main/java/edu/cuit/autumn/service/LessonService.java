package edu.cuit.autumn.service;

import edu.cuit.autumn.entity.Lesson;
import edu.cuit.autumn.entity.Subject;
import edu.cuit.autumn.entity.Teacher;

import java.util.List;

public interface LessonService {

    List<Lesson> getLessonByTeacher(Teacher teacher);

    List<Lesson> getLessonBySubject(Subject subject);

    void insertLesson(Lesson lesson);

    void deleteLesson(Lesson lesson);

    void updateLesson(Lesson lesson);

    List<Lesson> getAllLesson();

    List<Lesson> getLessonByIndex(int startIndex, int endIndex);

    Lesson getLessonByLessonId(String lessonId);

}
