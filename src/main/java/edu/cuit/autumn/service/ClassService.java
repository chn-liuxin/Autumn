package edu.cuit.autumn.service;

import edu.cuit.autumn.entity.Class;

import java.util.List;

public interface ClassService {

    List<Class> getAllClass();

    List<Class> getClassByMajor(String classMajor);

    List<Class> getClassBySession(Short classSession);

    void insertClass(Class aClass);

    void deleteClass(Class aClass);

    void updateClass(Class aClass);

}
