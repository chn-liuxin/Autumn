package edu.cuit.autumn.controller;

import edu.cuit.autumn.entity.ReviewExp;
import edu.cuit.autumn.entity.Teacher;
import edu.cuit.autumn.entity.User;
import edu.cuit.autumn.service.impl.ReviewExpServiceImpl;
import edu.cuit.autumn.service.impl.TeacherServiceImpl;
import edu.cuit.autumn.service.impl.UserServiceImpl;
import edu.cuit.autumn.util.AutoID;
import org.apache.ibatis.jdbc.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.expression.spel.ast.NullLiteral;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    UserServiceImpl userService;
    @Autowired
            TeacherServiceImpl teacherService;
    User user;

    @RequestMapping("/login-check")
    public String loginCheck(Model model, HttpServletRequest request) {
        String username = request.getParameter("userName");
        String password = request.getParameter("userPassword");
        user = userService.getUserByName(username);
        if (user != null) {
            System.out.println(user.toString());
            if (user.getUserPassword().equals(password)) {
                model.addAttribute(user);
                return "/page/index";
            }
        }
        model.addAttribute("message", "false");
        return "/page/login";
    }

    @RequestMapping("/userList")
    public String showAllUser(Model model) {
        List<User> users = userService.getAllUser();
        model.addAttribute("users", users);
        return "user/userList";
    }

    //    我的信息
    @RequestMapping("/MyInformation")
    public String myInformation(Model model, HttpServletRequest request) {
        String userName=request.getParameter("userName");
        if (userName!=null){
            System.out.println(userName);
            user=userService.getUserByName(userName);
            model.addAttribute(user);
            Teacher teacher=userService.getTeacherByUserId(user);
            model.addAttribute(teacher);
            return "/page/my-information";
        }
        model.addAttribute("message","请登录后进行操作");
        return "/page/login";
    }

    //    听课记录
    @RequestMapping("/SupervisionRecord")
    public String supervisionRecord(Model model,HttpServletRequest request) {
        user=new User();
        user.setUserName(request.getParameter("userName"));
        user.setUserId(request.getParameter("userId"));
        Teacher teacher=userService.getTeacherByUserId(user);
        ReviewExpServiceImpl reviewExpService=new ReviewExpServiceImpl();
        List<ReviewExp> list=reviewExpService.getReviewExpByReviewTeacherId(teacher.getTeacherId());
        model.addAttribute("reviewExps",list);
        return "/page/supervision-record";
    }

    //    信息管理
    @RequestMapping("/ManageInformation")
    public String manageInformation(Model model) {
        return "/page/manage-information";
    }

    //    申请听课
    @RequestMapping("/ApplySupervision")
    public String applySupervision(Model model) {
        return "/page/apply-supervision";
    }

    //    安排听课
    @RequestMapping("/ManageSupervision")
    public String manageSupervision(Model model) {

        return "/page/manage-supervision";
    }

    //    用户管理
    @RequestMapping("/ManageUser")
    public String manageUser(Model model,HttpServletRequest request) {
        String name=request.getParameter("teacherName");
        String id=request.getParameter("teacherId");
        if (name != null || id!=null) {
            Teacher teacher=teacherService.getTeacherById(id);
            System.out.println(id);
            model.addAttribute(teacher);
            return "/page/manage-user";
        }
        return "/page/manage-user";
    }

    //    录入评价表
    @RequestMapping("/EntryReview")
    public String entryReview(Model model) {
        return "/page/entry-review-theory";
    }

    //    历史记录
    @RequestMapping("/History")
    public String history(Model model) {
        return "/page/history";
    }
    @RequestMapping("/Top")
    public String head(Model model) {
        return "/page/index-top";
    }

    @RequestMapping("/Main")
    public String main(Model model,HttpServletRequest request) {
        if (request.getParameter("userName")!=null){
            System.out.println("123");
            model.addAttribute("userName",request.getParameter("userName"));
            return "/page/my-information";
        }
        return "/page/my-information";
    }

    @RequestMapping("/teacher1")
    public String teacher1(Model model) {
        User user=userService.getUserByName("root");
        System.out.println(user.getUserName());
        TeacherServiceImpl teacherService=new TeacherServiceImpl();
        Teacher teacher1=userService.getTeacherByUserId(user);
        System.out.println(teacher1.getTeacherName());
        Teacher teacher=teacherService.getTeacherByUserId(user);
        System.out.println(teacher.getTeacherName());
        return "/page/login";

    }
}