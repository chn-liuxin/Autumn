package edu.cuit.autumn.entity;

import java.util.Date;

public class ReviewTheory {
    private String reviewId;

    private String teacherId;

    private String reviewTeacherId;

    private String classId;

    private String subjectId;

    private String classroom;

    private String theme;

    private Date reviewDate;

    private String reviewTime;

    private Float score1;

    private Float score2;

    private Float score3;

    private Float score4;

    private Float score5;

    private Float score6;

    private Float score7;

    private Float score8;

    private Float score9;

    private Float score10;

    private Float totalScore;

    private Byte teacherSituation;

    private Byte atmosphere;

    private Byte studentSituation;

    private Byte bookSituation;

    private Byte headRate;

    private Byte sitSituation;

    private Byte teachType;

    private String overallEvaluation;

    private String recommend;

    public String getReviewId() {
        return reviewId;
    }

    public void setReviewId(String reviewId) {
        this.reviewId = reviewId == null ? null : reviewId.trim();
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId == null ? null : teacherId.trim();
    }

    public String getReviewTeacherId() {
        return reviewTeacherId;
    }

    public void setReviewTeacherId(String reviewTeacherId) {
        this.reviewTeacherId = reviewTeacherId == null ? null : reviewTeacherId.trim();
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId == null ? null : classId.trim();
    }

    public String getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId == null ? null : subjectId.trim();
    }

    public String getClassroom() {
        return classroom;
    }

    public void setClassroom(String classroom) {
        this.classroom = classroom == null ? null : classroom.trim();
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme == null ? null : theme.trim();
    }

    public Date getReviewDate() {
        return reviewDate;
    }

    public void setReviewDate(Date reviewDate) {
        this.reviewDate = reviewDate;
    }

    public String getReviewTime() {
        return reviewTime;
    }

    public void setReviewTime(String reviewTime) {
        this.reviewTime = reviewTime == null ? null : reviewTime.trim();
    }

    public Float getScore1() {
        return score1;
    }

    public void setScore1(Float score1) {
        this.score1 = score1;
    }

    public Float getScore2() {
        return score2;
    }

    public void setScore2(Float score2) {
        this.score2 = score2;
    }

    public Float getScore3() {
        return score3;
    }

    public void setScore3(Float score3) {
        this.score3 = score3;
    }

    public Float getScore4() {
        return score4;
    }

    public void setScore4(Float score4) {
        this.score4 = score4;
    }

    public Float getScore5() {
        return score5;
    }

    public void setScore5(Float score5) {
        this.score5 = score5;
    }

    public Float getScore6() {
        return score6;
    }

    public void setScore6(Float score6) {
        this.score6 = score6;
    }

    public Float getScore7() {
        return score7;
    }

    public void setScore7(Float score7) {
        this.score7 = score7;
    }

    public Float getScore8() {
        return score8;
    }

    public void setScore8(Float score8) {
        this.score8 = score8;
    }

    public Float getScore9() {
        return score9;
    }

    public void setScore9(Float score9) {
        this.score9 = score9;
    }

    public Float getScore10() {
        return score10;
    }

    public void setScore10(Float score10) {
        this.score10 = score10;
    }

    public Float getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(Float totalScore) {
        this.totalScore = totalScore;
    }

    public Byte getTeacherSituation() {
        return teacherSituation;
    }

    public void setTeacherSituation(Byte teacherSituation) {
        this.teacherSituation = teacherSituation;
    }

    public Byte getAtmosphere() {
        return atmosphere;
    }

    public void setAtmosphere(Byte atmosphere) {
        this.atmosphere = atmosphere;
    }

    public Byte getStudentSituation() {
        return studentSituation;
    }

    public void setStudentSituation(Byte studentSituation) {
        this.studentSituation = studentSituation;
    }

    public Byte getBookSituation() {
        return bookSituation;
    }

    public void setBookSituation(Byte bookSituation) {
        this.bookSituation = bookSituation;
    }

    public Byte getHeadRate() {
        return headRate;
    }

    public void setHeadRate(Byte headRate) {
        this.headRate = headRate;
    }

    public Byte getSitSituation() {
        return sitSituation;
    }

    public void setSitSituation(Byte sitSituation) {
        this.sitSituation = sitSituation;
    }

    public Byte getTeachType() {
        return teachType;
    }

    public void setTeachType(Byte teachType) {
        this.teachType = teachType;
    }

    public String getOverallEvaluation() {
        return overallEvaluation;
    }

    public void setOverallEvaluation(String overallEvaluation) {
        this.overallEvaluation = overallEvaluation == null ? null : overallEvaluation.trim();
    }

    public String getRecommend() {
        return recommend;
    }

    public void setRecommend(String recommend) {
        this.recommend = recommend == null ? null : recommend.trim();
    }
}