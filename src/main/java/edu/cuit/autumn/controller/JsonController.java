package edu.cuit.autumn.controller;

import edu.cuit.autumn.entity.JsonResult;
import edu.cuit.autumn.entity.Subject;
import edu.cuit.autumn.entity.Teacher;
import edu.cuit.autumn.service.impl.SubjectServiceImpl;
import edu.cuit.autumn.service.impl.TeacherServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class JsonController {

    @Autowired
    TeacherServiceImpl teacherService;
    @Autowired
    SubjectServiceImpl subjectService;

    @RequestMapping("SearchTeacherByName")
    public JsonResult searchTeacherByName(HttpServletRequest request) {
        List<Teacher> teachers;
        String teacherKeyword = request.getParameter("keyword");
        teachers = teacherService.getTeacherFuzzy(teacherKeyword);
        return new JsonResult(200, "success", teachers);
    }

    @RequestMapping("SearchCourseByName")
    public JsonResult searchCourseByName(HttpServletRequest request) {
        List<Subject> subjects;
        String subjectKeyword = request.getParameter("keyword");
        subjects = subjectService.getSubjectFuzzy(subjectKeyword);
        return new JsonResult(200, "success", subjects);
    }
}
