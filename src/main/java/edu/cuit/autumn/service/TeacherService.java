package edu.cuit.autumn.service;

import edu.cuit.autumn.entity.Teacher;

import java.util.List;

public interface TeacherService {

    // 查找所有老师
    List<Teacher> getAllTeacher();

    // 根据teacherName模糊查找用户
    List<Teacher> getTeacherByName(String teacherName);

    // 根据teacherId删除老师
    void deleteTeacher(String teacherId);

    // 增加老师
    void insertTeacher(Teacher teacher);

    // 修改老师信息
    void updateTeacher(Teacher teacher);

}