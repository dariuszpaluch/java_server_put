package com.dariuszpaluch.view_models;

import com.dariuszpaluch.models.Grade;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class GradeView extends Grade {
    String courseName;
    String studentName;
    

    public GradeView(Grade grade, String courseName, String studentName) {
        this.courseName = courseName;
        this.studentName = studentName;
        this.id = grade.getId();
        this.value = grade.getValue();
        this.created = grade.getCreated();
        this.studentIndex = grade.getStudentIndex();
    }


    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }
}
