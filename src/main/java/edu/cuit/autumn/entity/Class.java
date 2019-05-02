package edu.cuit.autumn.entity;

public class Class {
    private String classId;

    private Short classSession;

    private String classMajor;

    private Byte classNumber;

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId == null ? null : classId.trim();
    }

    public Short getClassSession() {
        return classSession;
    }

    public void setClassSession(Short classSession) {
        this.classSession = classSession;
    }

    public String getClassMajor() {
        return classMajor;
    }

    public void setClassMajor(String classMajor) {
        this.classMajor = classMajor == null ? null : classMajor.trim();
    }

    public Byte getClassNumber() {
        return classNumber;
    }

    public void setClassNumber(Byte classNumber) {
        this.classNumber = classNumber;
    }
}