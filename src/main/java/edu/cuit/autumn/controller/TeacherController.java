package edu.cuit.autumn.controller;

import edu.cuit.autumn.entity.Teacher;
import edu.cuit.autumn.entity.User;
import edu.cuit.autumn.service.impl.TeacherServiceImpl;
import edu.cuit.autumn.service.impl.UserServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
public class TeacherController {
    @RequestMapping("/teacher")
    public void teacher(Model model){
        UserServiceImpl userService=new UserServiceImpl();
        User user=userService.getUserByName("root");
        System.out.println(user.getUserName());
        TeacherServiceImpl teacherService=new TeacherServiceImpl();
        Teacher teacher=teacherService.getTeacherById("6666");
        System.out.println(teacher.getTeacherName());

    }
}
