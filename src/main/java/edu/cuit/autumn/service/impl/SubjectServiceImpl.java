package edu.cuit.autumn.service.impl;

import edu.cuit.autumn.entity.Subject;
import edu.cuit.autumn.mapper.SubjectMapper;
import edu.cuit.autumn.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubjectServiceImpl implements SubjectService {

    @Autowired
    SubjectMapper subjectMapper;

    @Override
    public List<Subject> getAllSubject() {
        return subjectMapper.getAllSubject();
    }

    @Override
    public List<Subject> getSubjectByType(String subjectType) {
        return subjectMapper.getSubjectByType(subjectType);
    }

    @Override
    public void insertSubject(Subject subject) {
        subjectMapper.insertSubject(subject);
    }

    @Override
    public void deleteSubject(Subject subject) {
        subjectMapper.deleteSubject(subject);
    }

    @Override
    public void updateSubject(Subject subject) {
        subjectMapper.updateSubject(subject);
    }

    @Override
    public Subject getSubjectById(String subjectId) {
        return subjectMapper.getSubjectById(subjectId);
    }

    @Override
    public List<Subject> getSubjectFuzzy(String subjectName){
        subjectName = new StringBuilder("%").append(subjectName).append("%").toString();
        return subjectMapper.getSubjectFuzzy(subjectName);
    }

    @Override
    public Subject getSubjectByName(String subjectName) {
        return subjectMapper.getSubjectByName(subjectName);
    }
}
