package edu.cuit.autumn.controller;

import edu.cuit.autumn.entity.Class;
import edu.cuit.autumn.entity.Lesson;
import edu.cuit.autumn.entity.Teacher;
import edu.cuit.autumn.service.LessonService;
import edu.cuit.autumn.service.impl.ClassServiceImpl;
import edu.cuit.autumn.service.impl.LessonServiceImpl;
import edu.cuit.autumn.service.impl.SubjectServiceImpl;
import edu.cuit.autumn.service.impl.TeacherServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class LessonController {

    @Autowired
    LessonServiceImpl lessonService;
    @Autowired
    TeacherServiceImpl teacherService;
    @Autowired
    SubjectServiceImpl subjectService;
    @Autowired
    ClassServiceImpl classService;

    @RequestMapping("/showTimeTable")
    public String showTimeTable(HttpServletRequest request, Model model) {
        List<Teacher> teachers = teacherService.getTeacherByName(request.getParameter("teacherName"));
        List<Lesson> lessons = lessonService.getLessonByTeacher(teachers.get(0));
        Class aClass = classService.getClassById(lessons.get(0).getClassId());
        model.addAttribute("lessons", lessons);
        model.addAttribute("subjectName", subjectService.getSubjectById(lessons.get(0).getSubjectId()).getSubjectName());
        model.addAttribute("teacherName", teacherService.getTeacherById(lessons.get(0).getTeacherId()).getTeacherName());
        model.addAttribute("classInfo", aClass.getClassSession() + "级" + aClass.getClassMajor() + aClass.getClassNumber() + "班");
        return "/common/showTimeTable";
    }

}
