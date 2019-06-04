package edu.cuit.autumn.controller;

import edu.cuit.autumn.entity.*;
import edu.cuit.autumn.service.impl.*;
import edu.cuit.autumn.util.AutoID;
import edu.cuit.autumn.util.ComputeAverageSorce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.bind.JAXB;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class MainController {

    @Autowired
    UserServiceImpl userService;
    @Autowired
    TeacherServiceImpl teacherService;
    @Autowired
    ReviewTheoryServiceImpl reviewTheoryService;
    @Autowired
    ReviewExpServiceImpl reviewExpService;
    @Autowired
    SubjectServiceImpl subjectService;
    @Autowired
            ClassServiceImpl classService;
    User user;
    Teacher teacher;
    Map<Short, String> identityMap = new HashMap<>();
    HttpSession session;

    public void initIdentityMap() {
        // 身份标识
        identityMap.put((short)1, "一级督导");
        identityMap.put((short)2, "普通督导");
        identityMap.put((short)3, "普通老师");
        identityMap.put((short)0, "管理员");
    }

    /**
     * 返回主页
     * @return
     */
    @RequestMapping("/index")
    public String login(Model model, HttpServletRequest request) {
        session = request.getSession();
        session.setAttribute("userName",null);
        model.addAttribute("userName",null);
        return "/page/login";
    }

    /**
     * Home
     */
    @RequestMapping("Home")
    public String home(Model model, HttpServletRequest request) {
        session = request.getSession();
        String userName= (String) session.getAttribute("userName");
        initIdentityMap();
        if (userName!=null){
            user=userService.getUserByName(userName);
            model.addAttribute("identityMap", identityMap);
            model.addAttribute(user);
        }
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
    public String entryReview(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String type = request.getParameter("type");
        if (type == null) {
            return "/page/entry-review-exp";
        } else {
            if (type.equals("type1")) {
                String teacherName = request.getParameter("teacherName");
                response.setContentType("text/xml;charset=UTF-8");
                List<Teacher> teachers = teacherService.getTeacherFuzzy(teacherName);
                StringWriter writer=new StringWriter();
                PrintWriter out = response.getWriter();
                Write(teachers,writer);
                out.write("<List>\n"+writer.toString()+"</List>");
                writer.close();
                out.close();
            }
            if(type.equals("type2")) {
                String subjectName = request.getParameter("subjectName");
                response.setContentType("text/xml;charset=UTF-8");
                List<Subject> subjects = subjectService.getSubjectFuzzy(subjectName);
                StringWriter writer=new StringWriter();
                PrintWriter out = response.getWriter();
                Write( subjects,writer);
                out.write("<List>\n"+writer.toString()+"</List>");
                writer.close();
                out.close();
            }
            if(type.equals("type3")){
                String classMajor = request.getParameter("classMajor");
                response.setContentType("text/xml;charset=UTF-8");
                List<edu.cuit.autumn.entity.Class> classes = classService.getClassFuzzy(classMajor);
                StringWriter writer=new StringWriter();
                PrintWriter out = response.getWriter();
                Write( classes,writer);
                out.write("<List>\n"+writer.toString()+"</List>");
                writer.close();
                out.close();
            }
            if(type.equals("type4")){
                StringWriter writer=new StringWriter();
                PrintWriter out = response.getWriter();
                ReviewExp reviewExp=new ReviewExp();
                System.out.println(request.getParameter("teacherId"));
                reviewExp.setTeacherId(request.getParameter("teacherId"));
                reviewExp.setReviewTeacherId(request.getParameter("teacherId1"));
                reviewExp.setSubjectId(request.getParameter("subjectId"));
                reviewExp.setClassId(request.getParameter("classId"));
                reviewExp.setTheme(request.getParameter("theme"));
                reviewExp.setReviewDate(java.sql.Date.valueOf(request.getParameter("reviewDate")));
                reviewExp.setClassroom(request.getParameter("classroom"));
                reviewExp.setScore1(Float.parseFloat(request.getParameter("score1")));
                reviewExp.setScore2(Float.parseFloat(request.getParameter("score2")));
                reviewExp.setScore3(Float.parseFloat(request.getParameter("score3")));
                reviewExp.setScore4(Float.parseFloat(request.getParameter("score4")));
                reviewExp.setScore5(Float.parseFloat(request.getParameter("score5")));
                reviewExp.setScore6(Float.parseFloat(request.getParameter("score6")));
                reviewExp.setScore7(Float.parseFloat(request.getParameter("score7")));
                reviewExp.setScore8(Float.parseFloat(request.getParameter("score8")));
                reviewExp.setManagement(Byte.parseByte(request.getParameter("score9")));
                reviewExp.setComment(request.getParameter("review"));
                reviewExp.setType((byte)1);
                reviewExp.setReviewId(AutoID.getAutoID());
                reviewExp.setTotalScore(ComputeAverageSorce.computeScore(reviewExp));
                reviewExpService.insertReviewTheory(reviewExp);
                out.write("sucssce");
                out.close();
                writer.close();
            }
        }
        return "/page/entry-review-exp";
    }
    void Write(List list,StringWriter writer){
        for(int i=0;i<list.size();i++) {
            StringWriter writer1 =new StringWriter();
            JAXB.marshal(list.get(i),writer1);
            String sss=writer1.toString().substring(56, writer1.toString().length());
            writer.write(sss);
            try {
                writer1.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
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
    /**
     * 录入理论课评价表
     * @return
     */
    @RequestMapping("/EntryReview")
    public String entryReview(Model model, HttpServletRequest request, HttpServletResponse response) throws IOException {
        String type=request.getParameter("type");
        if(type!=null) {
            if(type.equals("type4")){
                StringWriter writer=new StringWriter();
                PrintWriter out = response.getWriter();
                ReviewTheory reviewTheory=new ReviewTheory();
                reviewTheory.setTeacherId(request.getParameter("teacherId"));
                reviewTheory.setReviewTeacherId(request.getParameter("teacherId1"));
                reviewTheory.setSubjectId(request.getParameter("subjectId"));
                reviewTheory.setClassId(request.getParameter("classId"));
                reviewTheory.setTheme(request.getParameter("theme"));
                reviewTheory.setReviewDate(java.sql.Date.valueOf(request.getParameter("reviewDate")));
                reviewTheory.setClassroom(request.getParameter("classroom"));
                reviewTheory.setScore1(Float.parseFloat(request.getParameter("score1")));
                reviewTheory.setScore2(Float.parseFloat(request.getParameter("score2")));
                reviewTheory.setScore3(Float.parseFloat(request.getParameter("score3")));
                reviewTheory.setScore4(Float.parseFloat(request.getParameter("score4")));
                reviewTheory.setScore5(Float.parseFloat(request.getParameter("score5")));
                reviewTheory.setScore6(Float.parseFloat(request.getParameter("score6")));
                reviewTheory.setScore7(Float.parseFloat(request.getParameter("score7")));
                reviewTheory.setScore8(Float.parseFloat(request.getParameter("score8")));
                reviewTheory.setScore9(Float.parseFloat(request.getParameter("score9")));
                reviewTheory.setScore10(Float.parseFloat(request.getParameter("score10")));
                reviewTheory.setTeacherSituation(Byte.parseByte(request.getParameter("radio1")));
                reviewTheory.setAtmosphere(Byte.parseByte(request.getParameter("radio2")));
                reviewTheory.setStudentSituation(Byte.parseByte(request.getParameter("radio3")));
                reviewTheory.setBookSituation(Byte.parseByte(request.getParameter("radio4")));
                reviewTheory.setHeadRate(Byte.parseByte(request.getParameter("radio5")));
                reviewTheory.setSitSituation(Byte.parseByte(request.getParameter("radio6")));
                reviewTheory.setTeachType(Byte.parseByte(request.getParameter("radio7")));
                reviewTheory.setOverallEvaluation(request.getParameter("review"));
                reviewTheory.setRecommend(request.getParameter("other"));
                reviewTheory.setReviewId(AutoID.getAutoID());
                reviewTheory.setTotalScore(ComputeAverageSorce.computeScore(reviewTheory));
                reviewTheoryService.insertReviewTheory(reviewTheory);
                out.write("sucssce");
                out.close();
                writer.close();
            }
        }
        return "/page/entry-review-theory";
    }
}
