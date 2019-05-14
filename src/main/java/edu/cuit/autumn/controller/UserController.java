package edu.cuit.autumn.controller;

import edu.cuit.autumn.entity.User;
import edu.cuit.autumn.service.impl.UserServiceImpl;
import edu.cuit.autumn.util.AutoID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    UserServiceImpl userService;

    @RequestMapping("/index")
    public String login(Model model) {
        return "/page/login";
    }

    @RequestMapping("/register")
    public String register() {
        return "/page/register";
    }

    @RequestMapping("/register-check")
    public String register(HttpServletRequest request) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        short identity = -1;
        try {
            identity = Short.parseShort(request.getParameter("userIdentity").trim());
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        User user = new User();
        user.setUserId(AutoID.getAutoID());
        user.setUserName(username);
        user.setUserPassword(password);
        user.setUserIdentity(identity);
        if (userService.getUserByName(username) == null) {
            userService.insertUser(user);
            return "/page/login";
        }
        return "/page/register";
    }

    @RequestMapping("/login-check")
    public String loginCheck(Model model, HttpServletRequest request) {
        String username = request.getParameter("userName");
        String password = request.getParameter("userPassword");
        User user = userService.getUserByName(username);
        if (user != null) {
            System.out.println(user.toString());
            if (user.getUserPassword().equals(password)) {
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
    public String myIfformation(Model model) {
        return "/page/my-information";
    }

    //    听课记录
    @RequestMapping("/SupervisionRecord")
    public String supervisionRecord(Model model) {
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
    public String manageUser(Model model) {
        return "/page/manage-user";
    }

    //    录入评价表
    @RequestMapping("/EntryReview")
    public String entryReview(Model model) {
        return "/page/entry-review";
    }

    //    历史记录
    @RequestMapping("/History")
    public String history(Model model) {
        return "/page/history";
    }
    @RequestMapping("/Head")
    public String head(Model model) {
        return "/page/head";
    }
    @RequestMapping("/Menu")
    public String menu(Model model) {
        return "/page/menu";
    }
    @RequestMapping("/Main")
    public String main(Model model) {
        return "/page/my-information";
    }


}