package edu.cuit.autumn.entity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Subject {
    @XmlAttribute
    private String subjectId;
    @XmlAttribute
    private String subjectName;
    @XmlAttribute
    private Float subjectCredit;
    @XmlAttribute
    private Byte subjectHour;
    @XmlAttribute
    private String subjectType;
    @XmlAttribute
    private Byte subjectStartTime;
    @XmlAttribute
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