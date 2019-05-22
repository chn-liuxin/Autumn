package edu.cuit.autumn.controller;

import edu.cuit.autumn.entity.Teacher;
import edu.cuit.autumn.entity.User;
import edu.cuit.autumn.service.impl.TeacherServiceImpl;
import edu.cuit.autumn.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class MainController {

    @Autowired
    UserServiceImpl userService;
    @Autowired
    TeacherServiceImpl teacherService;
    User user;
    Teacher teacher;

    /**
     * 返回主页
     * @return
     */
    @RequestMapping("/index")
    public String login() {
        return "/page/login";
    }

    /**
     * Home
     */
    @RequestMapping("Home")
    public String home() {
        return "/page/home";
    }

    /**
     * 返回主页菜单
     * @return
     */
    @RequestMapping("/Menu")
    public String menu(Model model, HttpServletRequest request) {
        String userName = request.getParameter("userName");
        System.out.println(userName + "\tin menu.");
        user = userService.getUserByName(userName);
        teacher = teacherService.getTeacherByUserId(user);
        model.addAttribute("teacherName", teacher == null ? "管理员" : teacher.getTeacherName());
        model.addAttribute("userIdentity", user.getUserIdentity());
        return "/page/index-menu";
    }

    /**
     * 录入实验课评价表
     * @return
     */
    @RequestMapping("/EntryReviewExp")
    public String entryReview() {
        return "/page/entry-review-exp";
    }

    /**
     * 返回主页底部
     * @return
     */
    @RequestMapping("/Bottom")
    public String bottom() {
        System.out.println("into bottom");
        return "/page/index-bottom";
    }
}
