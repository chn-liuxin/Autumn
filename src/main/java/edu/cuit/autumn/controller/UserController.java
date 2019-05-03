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
        return "/common/login";
    }

    @RequestMapping("/register")
    public String register() {
        return "/common/register";
    }

    @RequestMapping("/registerCheck")
    public String register(HttpServletRequest request) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        short identity = -1;
        try{
            identity = Short.parseShort(request.getParameter("userIdentity").trim());
        }
        catch (NumberFormatException e) {
            e.printStackTrace();
        }
        User user = new User();
        user.setUserId(AutoID.getAutoID());
        user.setUserName(username);
        user.setUserPassword(password);
        user.setUserIdentity(identity);
        if (userService.getUserByName(username) == null) {
            userService.insertUser(user);
            return "/common/login";
        }
        return "/common/register";
    }

    @RequestMapping("/loginCheck")
    public String loginCheck(Model model, HttpServletRequest request) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        User user = userService.getUserByName(username);
        if (user != null) {
            System.out.println(user.toString());
            if (user.getUserPassword().equals(password)) {
                return "/common/index";
            }
        }
        return "/common/login";
    }

    @RequestMapping("/userList")
    public String showAllUser(Model model) {
        List<User> users = userService.getAllUser();
        model.addAttribute("users", users);
        return "user/userList";
    }

}