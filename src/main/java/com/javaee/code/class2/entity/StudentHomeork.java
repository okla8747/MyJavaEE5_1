package com.javaee.code.class2.entity;

import java.util.Date;

public class StudentHomeork {
    private long id;
    private long studentId;
    private long homeworkId;
    private String homeworkTitle;
    private String homeworkContent;
    private String submitTime;

    public StudentHomeork(long id, long studentId, long homeworkId, String homeworkTitle, String homeworkContent, String submitTime) {
        this.id = id;
        this.studentId = studentId;
        this.homeworkId = homeworkId;
        this.homeworkTitle = homeworkTitle;
        this.homeworkContent = homeworkContent;
        this.submitTime = submitTime;
    }

    public StudentHomeork(){

    }
    public long getId() {
        return id;
    }

    public long getStudentId() {
        return studentId;
    }

    public long getHomeworkId() {
        return homeworkId;
    }

    public String getHomeworkTitle() {
        return homeworkTitle;
    }

    public String getHomeworkContent() {
        return homeworkContent;
    }

    public String getSubmitTime() {
        return submitTime;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setStudentId(long studentId) {
        this.studentId = studentId;
    }

    public void setHomeworkId(long homeworkId) {
        this.homeworkId = homeworkId;
    }

    public void setHomeworkTitle(String homeworkTitle) {
        this.homeworkTitle = homeworkTitle;
    }

    public void setHomeworkContent(String homeworkContent) {
        this.homeworkContent = homeworkContent;
    }

    public void setSubmitTime(String submitTime) {
        this.submitTime = submitTime;
    }

    @Override
    public String toString() {
        return "StudentHomeork{" +
                "id=" + id +
                ", studentId=" + studentId +
                ", homeworkId=" + homeworkId +
                ", homeworkTitle='" + homeworkTitle + '\'' +
                ", homeworkContent='" + homeworkContent + '\'' +
                ", submitTime='" + submitTime + '\'' +
                '}';
    }
}
