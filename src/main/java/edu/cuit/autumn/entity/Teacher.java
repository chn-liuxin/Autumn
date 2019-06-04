package edu.cuit.autumn.entity;

import javax.xml.bind.annotation.*;
import java.util.Date;
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Teacher {
    @XmlAttribute
    private String teacherId;
    @XmlAttribute
    private String userId;
    @XmlAttribute
    private String teacherName;
    @XmlAttribute
    private String teacherSex;
    @XmlAttribute
    private Date teacherBirthday;
    @XmlAttribute
    private String teacherPosition;
    @XmlAttribute
    private String teacherPhone;

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId == null ? null : teacherId.trim();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName == null ? null : teacherName.trim();
    }

    public String getTeacherSex() {
        return teacherSex;
    }

    public void setTeacherSex(String teacherSex) {
        this.teacherSex = teacherSex == null ? null : teacherSex.trim();
    }

    public Date getTeacherBirthday() {
        return teacherBirthday;
    }

    public void setTeacherBirthday(Date teacherBirthday) {
        this.teacherBirthday = teacherBirthday;
    }

    public String getTeacherPosition() {
        return teacherPosition;
    }

    public void setTeacherPosition(String teacherPosition) {
        this.teacherPosition = teacherPosition == null ? null : teacherPosition.trim();
    }

    public String getTeacherPhone() {
        return teacherPhone;
    }

    public void setTeacherPhone(String teacherPhone) {
        this.teacherPhone = teacherPhone == null ? null : teacherPhone.trim();
    }
}