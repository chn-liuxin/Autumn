package edu.cuit.autumn.service.impl;

import edu.cuit.autumn.entity.Class;
import edu.cuit.autumn.mapper.ClassMapper;
import edu.cuit.autumn.service.ClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassServiceImpl implements ClassService {

    @Autowired
    ClassMapper classMapper;

    @Override
    public List<Class> getAllClass() {
        return classMapper.getAllClass();
    }

    @Override
    public List<Class> getClassByMajor(String classMajor) {
        return classMapper.getClassByMajor(classMajor);
    }

    @Override
    public List<Class> getClassBySession(Short classSession) {
        return classMapper.getClassBySession(classSession);
    }

    @Override
    public void deleteClass(Class aClass) {
        classMapper.deleteClass(aClass);
    }

    @Override
    public void insertClass(Class aClass) {
        classMapper.insertClass(aClass);
    }

    @Override
    public void updateClass(Class aClass) {
        classMapper.updateClass(aClass);
    }

    @Override
    public Class getClassById(String classId) {
        return classMapper.getClassById(classId);
    }

    @Override
    public List<Class> getClassFuzzy(String classMajor) {
        classMajor = new StringBuilder("%").append(classMajor).append("%").toString();
        return classMapper.getClassFuzzy(classMajor);
    };
}
