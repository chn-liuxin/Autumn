package edu.cuit.autumn.controller;

import edu.cuit.autumn.entity.Teacher;
import edu.cuit.autumn.entity.User;
import edu.cuit.autumn.service.impl.TeacherServiceImpl;
import edu.cuit.autumn.service.impl.UserServiceImpl;
import edu.cuit.autumn.util.AutoID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class ManageUserController {
    @Autowired
    UserServiceImpl userService;
    @Autowired
    TeacherServiceImpl teacherService;
    User user;
    Teacher teacher;
    List<Teacher> teachers;
    Map<String, User> userMap = new HashMap<>();
    Map<Short, String> identityMap = new HashMap<>();

    public void initIdentityMap() {
        // 身份标识
        identityMap.put((short)1, "一级督导");
        identityMap.put((short)2, "普通督导");
        identityMap.put((short)3, "普通老师");
    }

    public void findUser(List<Teacher> teachers, Map<String, User> userMap) {
        for (Teacher teacher: teachers) {
            user = userService.getUserById(teacher.getUserId());
            if (user != null) {
                userMap.put(teacher.getUserId(), user);
            }
        }
    }

    public void addModel(Model model, List<Teacher> teachers, Map<String, User> userMap, Map<Short, String> identityMap, int currentPage, int totalPage) {
        model.addAttribute("teachers", teachers);
        model.addAttribute("userMap", userMap);
        model.addAttribute("identityMap", identityMap);
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalPage", totalPage);
    }

    /**
     * 用户管理模块主页
     * @param model
     * @param request
     * @return
     */
    @RequestMapping("/ManageUser")
    public String manageUser(Model model, HttpServletRequest request) {
        initIdentityMap();
        int pageSize = 5;
        int pageCount;
        // 查询老师功能
        if (request.getParameter("teacherName") != null) {
            String teacherName = request.getParameter("teacherName");
            teachers = teacherService.getTeacherByName(teacherName);
            if (request.getParameter("teacherPosition") != null) {
                // 这里注意必须用迭代器,如果用ForEach循环会报错
                Iterator<Teacher> iterator = teachers.iterator();
                while (iterator.hasNext()) {
                    user = userService.getUserById(iterator.next().getUserId());
                    if (user.getUserIdentity() != Short.parseShort(request.getParameter("teacherPosition"))) {
                        iterator.remove();
                    }
                }
            }
            pageCount = teachers.size() / pageSize + 1;
        }
        // 删除用户功能
        else if(request.getParameter("deleteUserId") != null) {
            String deleteUserId = request.getParameter("deleteUserId");
            user = userService.getUserById(deleteUserId);
            teacher = teacherService.getTeacherByUserId(user);
            teacherService.deleteTeacher(teacher.getTeacherId());
            userService.deleteUser(user.getUserId());
            teachers = teacherService.getTeacherByIndex(0, 5);
        }
        // 普通显示用户功能
        else {
            teachers = teacherService.getTeacherByIndex(0, 5);
        }
        for (Teacher teacher: teachers) {
            user = userService.getUserById(teacher.getUserId());
            if (user != null) {
                userMap.put(teacher.getUserId(), user);
            }
        }
        pageCount = teacherService.getTeacherCount() > 0 ? (teacherService.getTeacherCount() - 1) / pageSize + 1 : 1;
        addModel(model, teachers, userMap, identityMap, 1, pageCount);
        return "/page/manage-user";
    }

    /**
     * 添加用户按钮
     * @return
     */
    @RequestMapping("/AddUser")
    public String addUser() {
        return "/page/add-user";
    }

    /**
     * 添加老师
     * @return
     */
    @RequestMapping("/AddTeacher")
    public String addTeacher(Model model, HttpServletRequest request) {
        user = new User();
        teacher = new Teacher();
        Date birthday = new Date();
        String teacherId = AutoID.getAutoID();
        String userId = AutoID.getAutoID();
        String userName = request.getParameter("teacherName").trim();
        if (request.getParameter("teacherName").trim().length() == 0) {
            return "/page/manage-user";
        }
        String teacherBirthday = request.getParameter("teacherBirthday").trim();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            birthday = simpleDateFormat.parse(teacherBirthday);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        int teacherCount = teacherService.getTeacherByName(request.getParameter("teacherName").trim()).size();
        if (teacherCount != 0) {
            userName = userName + teacherCount;
            // 防止之前的用户被删除留下缺口
            while (userService.getUserByName(userName) != null) {
                teacherCount += 1;
                userName = request.getParameter("teacherName").trim() + teacherCount;
            }
        }
        user.setUserId(userId);
        user.setUserName(userName);
        user.setUserPassword("123456");
        user.setUserIdentity(Short.parseShort(request.getParameter("teacherIdentity").trim()));
        teacher.setUserId(user.getUserId());
        teacher.setTeacherId(teacherId);
        teacher.setTeacherName(request.getParameter("teacherName").trim());
        teacher.setTeacherSex(request.getParameter("teacherSex").trim());
        teacher.setTeacherPosition(request.getParameter("teacherPosition").trim());
        teacher.setTeacherPhone(request.getParameter("teacherPhone").trim());
        teacher.setTeacherBirthday(birthday);
        teacherService.insertTeacher(teacher, user);    // 添加一个Teacher的同时新生产一个User
        model.addAttribute("user", user);
        model.addAttribute("teacher", teacher);
        return "/page/add-user-remind";
    }

    /**
     * 管理员修改老师信息(身份、职位)
     */
    @RequestMapping("UpdateInfo")
    public String updateTeacherInfo(HttpServletRequest request) {
        user = new User();
        teacher = new Teacher();
        String teacherId = request.getParameter("teacherId");
        String teacherIdentity = request.getParameter("teacherIdentity");
        String teacherPosition = request.getParameter("teacherPosition");
        teacher.setTeacherId(teacherId);
        teacher = teacherService.getTeacherById(teacherId);
        if (teacher != null) {
            user = userService.getUserById(teacher.getUserId());
            teacher.setTeacherPosition(teacherPosition);
            user.setUserIdentity(Short.parseShort(teacherIdentity));
            teacherService.updateTeacher(teacher);
            userService.updateUser(user);
        }
        return "/page/manage-user";
    }

    /**
     * 自定义分页显示用户
     */
    @RequestMapping("ShowUserPaging")
    public String showUserPaging(HttpServletRequest request, Model model) {
        initIdentityMap();
        int pageIndex = Integer.parseInt(request.getParameter("pageIndex"));
        int pageSize = Integer.parseInt(request.getParameter("pageSize"));
        int teacherCount = teacherService.getTeacherCount();
        int pageCount = teacherCount / pageSize + 1;
        if (pageIndex > pageCount) {
            pageIndex = pageCount;
            teachers = teacherService.getTeacherByIndex((pageCount - 1) * pageSize, pageSize);
        }
        else {
            teachers = teacherService.getTeacherByIndex((pageIndex - 1) * pageSize, pageSize);
        }
        findUser(teachers, userMap);
        addModel(model, teachers, userMap, identityMap, pageIndex, pageCount);
        return "/page/manage-user";
    }

    /**
     * 上一页/下一页显示用户
     */
    @RequestMapping("ManageUserPreviousNextPage")
    public String manageUserPreviousNextPage(HttpServletRequest request, Model model) {
        initIdentityMap();
        int currentPage = 1;
        String state = request.getParameter("state");
        int startIndex = Integer.parseInt(request.getParameter("startIndex"));
        int pageSize = Integer.parseInt(request.getParameter("pageSize"));
        int pageCount = teacherService.getTeacherCount() / pageSize + 1;
        if (state.equals("previous")) {
            currentPage = startIndex > 1 ?  startIndex - 1 : startIndex;
        }
        else if (state.equals("next")) {
            currentPage = startIndex >= pageCount ? pageCount : startIndex + 1;
        }
        teachers = teacherService.getTeacherByIndex((currentPage - 1) * pageSize, pageSize);
        findUser(teachers, userMap);
        addModel(model, teachers, userMap, identityMap,currentPage, pageCount);
        return "/page/manage-user";
    }

}
