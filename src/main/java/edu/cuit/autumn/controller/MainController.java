package edu.cuit.autumn.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class MainController {

    /**
     * 返回主页
     * @return
     */
    @RequestMapping("/index")
    public String login() {
        return "/page/login";
    }

    /**
     * 返回主页菜单
     * @return
     */
    @RequestMapping("/Menu")
    public String menu(Model model, HttpServletRequest request) {
        String userIdentity = request.getParameter("userIdentity");
        model.addAttribute("userIdentity", userIdentity);
        System.out.println(userIdentity);
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
