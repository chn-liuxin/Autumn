package edu.cuit.autumn.service;

import edu.cuit.autumn.entity.Subject;

import java.util.List;

public interface SubjectService {

    List<Subject> getAllSubject();

    List<Subject> getSubjectByType(String subjectType);

    void insertSubject(Subject subject);

    void deleteSubject(Subject subject);

    void updateSubject(Subject subject);

    Subject getSubjectById(String subjectId);

}
