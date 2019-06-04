package edu.cuit.autumn.entity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Class {

    /**
     * @Param classId：主键ID
     * @Param classSession：年级,如:16级
     * @Param classMajor：专业名称,如:计算机科学与技术
     * @Param classNumber：班级,如:2班
     */
    @XmlAttribute
    private String classId;
    @XmlAttribute
    private Short classSession;
    @XmlAttribute
    private String classMajor;
    @XmlAttribute
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