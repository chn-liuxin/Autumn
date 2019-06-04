package edu.cuit.autumn.controller;

import edu.cuit.autumn.entity.*;
import edu.cuit.autumn.service.impl.*;
import edu.cuit.autumn.util.AutoID;
import edu.cuit.autumn.util.ConvertLesson;
import edu.cuit.autumn.util.RegularExpression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * 申请听课Controller
 */
@Controller
public class ApplySupervisionController {
    @Autowired
    LessonServiceImpl lessonService;
    @Autowired
    TeacherServiceImpl teacherService;
    @Autowired
    SubjectServiceImpl subjectService;
    @Autowired
    UserServiceImpl userService;
    @Autowired
    RecordServiceImpl recordService;
    Teacher teacher;
    User user;
    Lesson lesson;
    Subject subject;
    Record record;

    /**
     * 根据老师返回课程表
     */
    @RequestMapping("ApplySupervisionBySchoolTable")
    public String applySupervisionBySchoolTable(HttpServletRequest request, Model model) {
        List<Lesson> lessons;
        List<Subject> subjects;
        Map<Integer, Lesson> teacherLessonMap = new HashMap();
        Map<String, Subject> subjectMap = new HashMap<>();
        String userName = request.getParameter("userName");
        String teachingTeacherName = request.getParameter("teachingTeacherName");
        String teachingTeacherId = RegularExpression.match(teachingTeacherName);
        try {
            teacherService.getTeacherById(teachingTeacherId);
        }
        catch (Exception e) {
            model.addAttribute("userName", userName);
            model.addAttribute("displayTable", false);
            return "/page/apply-supervision";
        }
        if (userName.equals(teachingTeacherName)) {
            model.addAttribute("displayTable", false);
            return "/page/apply-supervision";
        }
        teacher = teacherService.getTeacherById(teachingTeacherId);
        lessons = lessonService.getLessonByTeacher(teacher);
        subjects = subjectService.getAllSubject();
        // 转换课程每天节次信息
        for (Lesson lesson: lessons) {
            int time = ConvertLesson.convertLessonTime(lesson.getLessonDay(), lesson.getLessonTime());
            teacherLessonMap.put(time, lesson);
        }
        lesson = new Lesson();
        lesson.setLessonRoom("");
        lesson.setSubjectId("");
        for (int i = 1; i <= 7; i ++) {
            for (int j = 1; j <= 6; j ++) {
                int key = i * 10 + j;
                if (!teacherLessonMap.containsKey(key)) {
                    teacherLessonMap.put(key, lesson);
                }
            }
        }
        // 将Subject信息放入Map
        for (Subject subject: subjects) {
            subjectMap.put(subject.getSubjectId(), subject);
        }
        subject = new Subject();
        subject.setSubjectName("");
        subjectMap.put("", subject);
        System.out.println("applyUserName:" + userName + "\tteachingTeacherName:" + teachingTeacherName);
        model.addAttribute("userName", userName);
        model.addAttribute("teachingTeacherName", teacher.getTeacherName());
        model.addAttribute("teacherLessonMap", teacherLessonMap);
        model.addAttribute("subjectMap", subjectMap);
        model.addAttribute("displayTable", true);
        return "/page/apply-supervision";
    }

    /**
     * 自主听课选择听课节次
     */
    @RequestMapping("ChooseCourse")
    public String chooseCourse(HttpServletRequest request, Model model) {
        String userName = request.getParameter("userName");
        String teachingTeacherName = request.getParameter("teachingTeacherName");
        String teachingTeacherId = RegularExpression.match(teachingTeacherName);
        String lessonId = request.getParameter("lessonId");
        record = new Record();
        record.setRecordId(AutoID.getAutoID());
        record.setType(Byte.parseByte("0"));
        record.setTeacherId(teachingTeacherId);
        record.setReviewTeacherId(teacherService.getTeacherByUserId(userService.getUserByName(userName)).getTeacherId());
        record.setLessonId(lessonId);
        record.setDatetime(new Date());
        record.setIsHandle(0);
        record.setStatus(1);
        record.setReviewType(0);
        record.setReviewId("");
        record.setIsDelete(false);
        try {
            Record checkRecord = recordService.getRecordByTeacherId(record.getTeacherId()).get(0);
            if ((checkRecord.getReviewTeacherId().equals(record.getReviewTeacherId())) && (checkRecord.getLessonId().equals(record.getLessonId()))) {
                model.addAttribute("userName", userName);
                model.addAttribute("teachingTeacherName", teachingTeacherName);
                return "/page/apply-supervision";
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        recordService.insertRecord(record);
        model.addAttribute("userName", userName);
        model.addAttribute("teachingTeacherName", teachingTeacherName);
        return "/page/apply-supervision";
    }

    /**
     * 以列表形式返回课程信息
     */
    @RequestMapping("ApplySupervisionByList")
    public String applySupervisionByList(HttpServletRequest request, Model model) {
        String userName = request.getParameter("userName");
        String teachingTeacherName = request.getParameter("teachingTeacherName");
        String teachingTeacherId = RegularExpression.match(teachingTeacherName);
        String teachingTeacherCourseName = request.getParameter("teachingTeacherCourseName");
        String teachingTeacherCourseId = RegularExpression.match(teachingTeacherCourseName);
        List<Lesson> lessons = new LinkedList<>();
        Map<String, Teacher> teachersMap = new HashMap<>();
        Map<String, Subject> subjectMap = new HashMap<>();
        if (teachingTeacherName.length() > 0 && teachingTeacherCourseName.length() > 0) {
            // 两个查询条件
            try {
                teacher = teacherService.getTeacherById(teachingTeacherId);
            }
            catch (Exception e) {
                model.addAttribute("userName", userName);
                model.addAttribute("displayList", false);
                return "/page/apply-supervision";
            }
            subject = subjectService.getSubjectById(teachingTeacherCourseId);
            lessons = lessonService.getLessonBySubject(subject);
            Iterator<Lesson> lessonIterator = lessons.iterator();
            while (lessonIterator.hasNext()) {
                if (!lessonIterator.next().getTeacherId().equals(teacher.getTeacherId())) {
                    lessonIterator.remove();
                }
            }
        }
        else if(teachingTeacherName.length() == 0 && teachingTeacherCourseName.length() > 0) {
            // 一个查询条件
            subject = subjectService.getSubjectById(teachingTeacherCourseId);
            lessons = lessonService.getLessonBySubject(subject);
        }
        for(Lesson lesson: lessons) {
            if (teachersMap.get(lesson.getTeacherId()) == null) {
                System.out.println("teachersMap add:" + teacherService.getTeacherById(lesson.getTeacherId()).getTeacherName());
                teachersMap.put(lesson.getTeacherId(), teacherService.getTeacherById(lesson.getTeacherId()));
            }
            if (subjectMap.get(lesson.getSubjectId()) == null) {
                System.out.println("subjectMap add:" + subjectService.getSubjectById(lesson.getSubjectId()).getSubjectName());
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

}
