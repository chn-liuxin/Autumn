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
    public void insertTeacher(Teacher teacher) {
        teacherMapper.insertTeacher(teacher);
        User user = new User();
        user.setUserId(teacher.getUserId());
        user.setUserName(teacher.getTeacherName());
        user.setUserPassword("123456");
        switch (teacher.getTeacherPosition()) {
            case "普通老师":
                user.setUserIdentity(Short.parseShort("1"));
                break;
            case "督导老师":
                user.setUserIdentity(Short.parseShort("2"));
                break;
            case "一级督导老师":
                user.setUserIdentity(Short.parseShort("3"));
                break;
            default:
                user.setUserIdentity(Short.parseShort("0"));
        }
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

}
