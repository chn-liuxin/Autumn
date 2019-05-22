package edu.cuit.autumn.service;

import edu.cuit.autumn.entity.Teacher;
import edu.cuit.autumn.entity.User;
import java.util.List;

public interface TeacherService {

    // 查找所有老师
    List<Teacher> getAllTeacher();

    // 根据teacherName模糊查找用户
    List<Teacher> getTeacherByName(String teacherName);

    // 根据teacherId删除老师
    void deleteTeacher(String teacherId);

    // 增加老师
    void insertTeacher(Teacher teacher, User user);

    // 修改老师信息
    void updateTeacher(Teacher teacher);

    //根据userId查找老师
    Teacher getTeacherByUserId(User user);

    //根据老师id查询
    Teacher getTeacherById(String teacherId);

}
