package edu.cuit.autumn.entity;

public class Subject {
    private String subjectId;

    private String subjectName;

    private Float subjectCredit;

    private Byte subjectHour;

    private String subjectType;

    private Byte subjectStartTime;

    private Byte subjectEndTime;

    public String getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId == null ? null : subjectId.trim();
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName == null ? null : subjectName.trim();
    }

    public Float getSubjectCredit() {
        return subjectCredit;
    }

    public void setSubjectCredit(Float subjectCredit) {
        this.subjectCredit = subjectCredit;
    }

    public Byte getSubjectHour() {
        return subjectHour;
    }

    public void setSubjectHour(Byte subjectHour) {
        this.subjectHour = subjectHour;
    }

    public String getSubjectType() {
        return subjectType;
    }

    public void setSubjectType(String subjectType) {
        this.subjectType = subjectType == null ? null : subjectType.trim();
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
}