package edu.cuit.autumn.controller;

import edu.cuit.autumn.entity.*;
import edu.cuit.autumn.service.impl.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Controller
public class UserController {

    @Autowired
    UserServiceImpl userService;
    @Autowired
    TeacherServiceImpl teacherService;
    @Autowired
    LessonServiceImpl lessonService;
    @Autowired
    SubjectServiceImpl subjectService;
    @Autowired
    RecordServiceImpl recordService;
    User user;
    Teacher teacher;
    Map<Short, String> identityMap = new HashMap<>();
    Map<String, String> resultMap = new HashMap<>();
    HttpSession session;

    public void initIdentityMap() {
        // 身份标识
        identityMap.put((short)1, "一级督导");
        identityMap.put((short)2, "普通督导");
        identityMap.put((short)3, "普通老师");
        identityMap.put((short)0, "管理员");
    }

    @RequestMapping("/home")
    public String loginCheck(Model model, HttpServletRequest request) {
        String username = request.getParameter("userName");
        String password = request.getParameter("userPassword");
        session = request.getSession();
        String code = request.getParameter("code");
        String checkCode = request.getParameter("checkCode");
        initIdentityMap();
        if(session.getAttribute("userName")==null) {
            model.addAttribute("userName",username);
            if(code != null && checkCode != null){
                code = code.toUpperCase();
                checkCode = checkCode.toUpperCase();
            }
            user = userService.getUserByName(username);
            if (user != null) {

                if (user.getUserPassword().equals(password)) {
                    if(code.equals(checkCode)){
                        model.addAttribute(user);
                        session.setAttribute("userName", username);
                        return "/page/index";
                    }else{
                        if(code.equals("")){
                            model.addAttribute("message", "请输入验证码!");
                        }else{
                            model.addAttribute("message", "验证码错误！");
                        }
                    }
                } else{
                    model.addAttribute("message", "请输入正确的用户名密码！");
                }
            } else{
                if(username != null){
                    model.addAttribute("message", "请输入正确的用户名！");
                }
            }

            return "/page/login";
        }else{
            model.addAttribute("identityMap", identityMap);
            user = userService.getUserByName((String) session.getAttribute("userName"));
            model.addAttribute(user);
            teacher=userService.getTeacherByUserId(user);
            model.addAttribute(teacher);
            return "/page/index";
        }
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
        session = request.getSession();
        String userName= (String) session.getAttribute("userName");
        initIdentityMap();
        if (userName!=null){
            System.out.println(userName + "我的信息");
            user=userService.getUserByName(userName);
            model.addAttribute("identityMap", identityMap);
            model.addAttribute(user);
            teacher=userService.getTeacherByUserId(user);
            model.addAttribute(teacher);
            return "/page/my-information";
        }
        model.addAttribute("message","请登录后进行操作");
        return "/page/login";
    }

    //    听课记录
    @RequestMapping("/SupervisionRecord")
    public String supervisionRecord(Model model,HttpServletRequest request) {
        if (request.getParameter("type")!=null) {
            String type=request.getParameter("type");
            String recordId=request.getParameter("recordId");
            if(type.equals("agree")) {
                recordService.changeStatus(recordId,(byte)2);

            }
            if(type.equals("refuse")) {
                recordService.changeStatus(recordId,(byte)3);
            }
            if(type.equals("delete")) {
                recordService.toDelete(recordId);
            }
        }else {
            String userName = request.getParameter("userName");
            User user = userService.getUserByName(userName);
            Teacher teacher = teacherService.getTeacherByUserId(user);
            List<Record> reviewTeacherList = recordService.getRecordByReviewTeacherId(teacher.getTeacherId());
            List<Record> teacherList = recordService.getRecordByTeacherId(teacher.getTeacherId());
            List<TeaSubRecLes> review = new LinkedList<>();
            List<TeaSubRecLes> teach = new LinkedList<>();
            for (int i = 0; i < reviewTeacherList.size(); i++) {
                if (reviewTeacherList.get(i).getIsDelete() == true) {
                    reviewTeacherList.remove(i);
                    i--;
                } else {
                    TeaSubRecLes teaSubRecLes = new TeaSubRecLes();
                    teaSubRecLes.setRecord(reviewTeacherList.get(i));
                    teaSubRecLes.setSubject(subjectService.getSubjectById(lessonService.getLessonByLessonId(reviewTeacherList.get(i).getLessonId()).getSubjectId()));
                    teaSubRecLes.setTeacher(teacherService.getTeacherById(reviewTeacherList.get(i).getTeacherId()));
                    teaSubRecLes.setLesson(lessonService.getLessonByLessonId(reviewTeacherList.get(i).getLessonId()));
                    review.add(teaSubRecLes);
                }
            }
            for (int i = 0; i < teacherList.size(); i++) {
                if (teacherList.get(i).getIsDelete() == true || teacherList.get(i).getType() == 1) {
                    teacherList.remove(i);
                    i--;
                } else {
                    TeaSubRecLes teaSubRecLes = new TeaSubRecLes();
                    teaSubRecLes.setRecord(teacherList.get(i));
                    teaSubRecLes.setSubject(subjectService.getSubjectById(lessonService.getLessonByLessonId(teacherList.get(i).getLessonId()).getSubjectId()));
                    teaSubRecLes.setTeacher(teacherService.getTeacherById(teacherList.get(i).getReviewTeacherId()));
                    teaSubRecLes.setLesson(lessonService.getLessonByLessonId(teacherList.get(i).getLessonId()));
                    teach.add(teaSubRecLes);
                }
            }
            model.addAttribute("review", review);
            model.addAttribute("teach", teach);
        }
        return "/page/manage-news";
    }


    //    信息管理
    @RequestMapping("/ManageInformation")
    public String manageInformation(Model model ,HttpServletRequest request) {
        session = request.getSession();
        String userName= (String) session.getAttribute("userName");
        System.out.println(userName);
        initIdentityMap();
        if (userName!=null){
            user=userService.getUserByName(userName);
            model.addAttribute("identityMap", identityMap);
            model.addAttribute(user);
            teacher=userService.getTeacherByUserId(user);
            model.addAttribute(teacher);
            return "/page/manage-information";
        }else {
            model.addAttribute("message", "请登录后进行操作");
            return "/page/login";
        }
    }
    //    申请听课
    @RequestMapping("/ApplySupervision")
    public String applySupervision(HttpServletRequest request, Model model) {
        String userName = request.getParameter("userName");
        List<Lesson> lessons = new LinkedList<>();
        Map<String, Teacher> teachersMap = new HashMap<>();
        Map<String, Subject> subjectMap = new HashMap<>();
        int index = lessonService.getAllLesson().size() - 5 > 0 ? lessonService.getAllLesson().size() - 5 : 0;
        int startIndex = (int)(Math.random() * index);
        lessons = lessonService.getLessonByIndex(startIndex, 5);
        for (Lesson  lesson:lessons) {
            if (teachersMap.get(lesson.getTeacherId()) == null) {
                teachersMap.put(lesson.getTeacherId(), teacherService.getTeacherById(lesson.getTeacherId()));
            }
            if (subjectMap.get(lesson.getSubjectId()) == null) {
                subjectMap.put(lesson.getSubjectId(), subjectService.getSubjectById(lesson.getSubjectId()));
            }
        }
        model.addAttribute("userName", userName);
        model.addAttribute("lessonList", lessons);
        model.addAttribute("subjectMap", subjectMap);
        model.addAttribute("teacherMap", teachersMap);
        model.addAttribute("displayList", true);
        return "/page/apply-supervision";
    }

    //    安排听课
    @RequestMapping("/ManageSupervision")
    public String manageSupervision(Model model) {
        List<Lesson> lessons = new LinkedList<>();
        Map<String, Teacher> teachersMap = new HashMap<>();
        Map<String, Subject> subjectMap = new HashMap<>();
        int index = lessonService.getAllLesson().size() - 5 > 0 ? lessonService.getAllLesson().size() - 5 : 0;
        int startIndex = (int)(Math.random() * index);
        lessons = lessonService.getLessonByIndex(startIndex, 5);
        for (Lesson  lesson:lessons) {
            if (teachersMap.get(lesson.getTeacherId()) == null) {
                teachersMap.put(lesson.getTeacherId(), teacherService.getTeacherById(lesson.getTeacherId()));
            }
            if (subjectMap.get(lesson.getSubjectId()) == null) {
                subjectMap.put(lesson.getSubjectId(), subjectService.getSubjectById(lesson.getSubjectId()));
            }
        }
        model.addAttribute("lessonList", lessons);
        model.addAttribute("subjectMap", subjectMap);
        model.addAttribute("teacherMap", teachersMap);
        model.addAttribute("displayList", true);
        return "/page/manage-supervision";
    }

    //    历史记录
    @RequestMapping("/History")
    public String history(Model model) {
        return "/page/history";
    }
    @RequestMapping("/Top")
    public String head(Model model, HttpServletRequest request) {
        String userName = (String) session.getAttribute("userName");
        model.addAttribute("userName", userName);
        return "/page/index-top";
    }

    @RequestMapping("/Main")
    public String main(Model model,HttpServletRequest request) {
        if (request.getParameter("userName")!=null){
            model.addAttribute("userName",request.getParameter("userName"));
            return "/page/my-information";
        }
        return "/page/my-information";
    }

    //    编辑信息
    @RequestMapping("EditInfo")
    public String editInformation(Model model ,HttpServletRequest request) throws ParseException {
        initIdentityMap();
        session = request.getSession();
        String userName = (String) session.getAttribute("userName");
        String teacherBirthday=request.getParameter("teacherBirthday").trim();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        if (userName!=null) {
            user = userService.getUserByName(userName);
            userName = request.getParameter("userName").trim();
            teacher = userService.getTeacherByUserId(user);
            user.setUserName(userName);
            teacher.setTeacherBirthday(simpleDateFormat.parse(teacherBirthday));
            teacher.setTeacherPhone(request.getParameter("teacherPhone").trim());
            model.addAttribute(user);
            model.addAttribute(teacher);
            model.addAttribute("identityMap", identityMap);
            userService.updateUser(user);
            teacherService.updateTeacher(teacher);
            session.setAttribute("userName",userName);
            return "/page/my-information";
        }else {
            model.addAttribute("message", "请登录后进行操作");
            return "/page/login";

        }
    }

    @RequestMapping("EditPwd")
    public String editPwd(Model model ,HttpServletRequest request) {
        initIdentityMap();
        session = request.getSession();
        String userName = (String) session.getAttribute("userName");
        if(userName!=null){
            user = userService.getUserByName(userName);
            String oldPwd = request.getParameter("oldPwd");
            String newPwd = request.getParameter("newPwd");
            String surePwd = request.getParameter("surePwd");
            teacher = userService.getTeacherByUserId(user);
            model.addAttribute(user);
            model.addAttribute(teacher);
            model.addAttribute("identityMap", identityMap);
            if (user.getUserPassword().equals(oldPwd)&&newPwd.equals(surePwd)) {
                if(oldPwd.equals(newPwd)){
                    model.addAttribute("message", "新旧密码相同！");
                    return "/page/manage-information";
                }
                user.setUserPassword(newPwd);
                userService.updateUser(user);
                return "/page/my-information";
            }else{
                model.addAttribute("message", "密码错误！");
                return "/page/manage-information";
            }
        }
        return "/page/login";
    }

        @RequestMapping("/ManageInfoCheck")
        @ResponseBody
        public Map<String, String> manageInfoCheck(String userName)  {
            if(userService.getUserByName(userName)==null||userName.equals(session.getAttribute("userName"))){
                resultMap.put("result","ok");
            }else{
                resultMap.put("result","error");
            }
            return resultMap;

        }

}