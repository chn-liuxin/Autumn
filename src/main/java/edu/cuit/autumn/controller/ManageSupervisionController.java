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
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ManageSupervisionController {
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

    @RequestMapping("ManageSupervisionBySchoolTable")
    public String manageSupervisionBySchoolTable(HttpServletRequest request, Model model) {
        List<Lesson> lessons;
        List<Subject> subjects;
        Map<Integer, Lesson> teacherLessonMap = new HashMap();
        Map<String, Subject> subjectMap = new HashMap<>();
        String teachingTeacherName = request.getParameter("teachingTeacherName");
        String teachingTeacherId = RegularExpression.match(teachingTeacherName);
        if (teacherService.getTeacherById(teachingTeacherId) == null) {
            model.addAttribute("displayTable", false);
            return "/page/manage-supervision";
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
        model.addAttribute("teachingTeacherName", teacher.getTeacherName());
        model.addAttribute("teachingTeacher", teacher);
        model.addAttribute("teacherLessonMap", teacherLessonMap);
        model.addAttribute("subjectMap", subjectMap);
        model.addAttribute("displayTable", true);
        return "/page/manage-supervision";
    }

    /**
     * 督导老师强制安排老师听课
     */
    @RequestMapping("ArrangeTeacherAttendClasses")
    public String arrangeTeacherAttendClasses(HttpServletRequest request) {
        String listenTeacherName = request.getParameter("listenTeacherName");
        String teachingTeacherId = request.getParameter("teachingTeacherId");
        String lessonId = request.getParameter("lessonId");
        String listenTeacherId = RegularExpression.match(listenTeacherName);
        record = new Record();
        record.setRecordId(AutoID.getAutoID());
        record.setType(Byte.parseByte("1"));
        record.setTeacherId(teachingTeacherId);
        record.setReviewTeacherId(listenTeacherId);
        record.setLessonId(lessonId);
        record.setDatetime(new Date());
        record.setIsHandle(0);
        record.setStatus(1);
        record.setReviewType(0);
        record.setReviewId("");
        record.setIsDelete(false);
        try {
            Record checkRecord = recordService.getRecordByTeacherId(record.getTeacherId()).get(0);
            if ((checkRecord.getReviewTeacherId().equals(record.getReviewTeacherId())) && (checkRecord.getLessonId().equals(record.getLessonId())) && (checkRecord.getType() == record.getType())) {
                return "/page/apply-supervision";
            }
        }
        catch (Exception e) {
        }
        recordService.insertRecord(record);
        return "/page/manage-supervision";
    }
}
