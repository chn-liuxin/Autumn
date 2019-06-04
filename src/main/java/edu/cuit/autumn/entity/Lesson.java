package edu.cuit.autumn.entity;

public class Lesson {
    private String lessonId;

    private String subjectId;

    private String teacherId;

    private String classId;

    private Byte subjectStartTime;

    private Byte subjectEndTime;

    private String lessonDay;

    private String lessonTime;

    private String lessonRoom;

    public String getLessonId() {
        return lessonId;
    }

    public void setLessonId(String lessonId) {
        this.lessonId = lessonId == null ? null : lessonId.trim();
    }

    public String getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId == null ? null : subjectId.trim();
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId == null ? null : teacherId.trim();
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId == null ? null : classId.trim();
    }

    public Byte getSubjectStartTime() {
        return subjectStartTime;
    }

    public void setSubjectStartTime(Byte subjectStartTime) {
        this.subjectStartTime = subjectStartTime;
    }

    public Byte getSubjectEndTime() {
        return subjectEndTime;
    }

    public void setSubjectEndTime(Byte subjectEndTime) {
        this.subjectEndTime = subjectEndTime;
    }

    public String getLessonDay() {
        return lessonDay;
    }

    public void setLessonDay(String lessonDay) {
        this.lessonDay = lessonDay;
    }

    public String getLessonTime() {
        return lessonTime;
    }

    public void setLessonTime(String lessonTime) {
        this.lessonTime = lessonTime == null ? null : lessonTime.trim();
    }

    public String getLessonRoom() {
        return lessonRoom;
    }

    public void setLessonRoom(String lessonRoom) {
        this.lessonRoom = lessonRoom == null ? null : lessonRoom.trim();
    }
}