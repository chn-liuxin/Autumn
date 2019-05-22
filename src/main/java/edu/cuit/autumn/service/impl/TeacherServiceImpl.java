package edu.cuit.autumn.service.impl;

import edu.cuit.autumn.entity.Teacher;
import edu.cuit.autumn.entity.User;
import edu.cuit.autumn.mapper.TeacherMapper;
import edu.cuit.autumn.mapper.UserMapper;
import edu.cuit.autumn.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    TeacherMapper teacherMapper;
    @Autowired
    UserMapper userMapper;

    @Override
    public List<Teacher> getAllTeacher() {
        return teacherMapper.getAllTeacher();
    }

    @Override
    public List<Teacher> getTeacherByName(String teacherName) {
        return teacherMapper.getTeacherByName(teacherName);
    }

    // 新增一个老师的同时在用户表里添加一个用户
    @Override
    public void insertTeacher(Teacher teacher, User user) {
        teacherMapper.insertTeacher(teacher);
        userMapper.insertUser(user);
    }

    @Override
    public void deleteTeacher(String teacherId) {
        teacherMapper.deleteTeacher(teacherId);
    }

    @Override
    public void updateTeacher(Teacher teacher) {
        teacherMapper.updateTeacher(teacher);
    }

    @Override
    public  Teacher getTeacherByUserId(User user){
        return teacherMapper.getTeacherByUserId(user);
    }
    @Override
    public Teacher getTeacherById(String teacherId) {
        return teacherMapper.getTeacherById(teacherId);
    }
}
