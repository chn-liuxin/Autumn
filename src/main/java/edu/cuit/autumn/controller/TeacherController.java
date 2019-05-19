package edu.cuit.autumn.controller;

import edu.cuit.autumn.entity.Teacher;
import edu.cuit.autumn.entity.User;
import edu.cuit.autumn.service.impl.TeacherServiceImpl;
import edu.cuit.autumn.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
public class TeacherController {
    @Autowired
    UserServiceImpl userService;
    @Autowired
    TeacherServiceImpl teacherService;
    @RequestMapping("/teacher")
    public String teacher(Model model){
        User user=userService.getUserByName("root");
        System.out.println(user.getUserId());
        Teacher teacher1=userService.getTeacherByUserId(user);
        System.out.println(teacher1.getTeacherName());
        Teacher teacher=teacherService.getTeacherByUserId(user);
        System.out.println(teacher.getTeacherName());
        return "/page/login";
    }
}
