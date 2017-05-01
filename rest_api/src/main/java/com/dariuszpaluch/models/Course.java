package com.dariuszpaluch.models;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement
public class Course {
    @NotNull
    private int id;

    @NotNull
    private String name;
    @NotNull
    private String teacher;

    List<Grade> grades;

    public Course() {
    }

    public Course(String name, String teacher) {
        this.name = name;
        this.teacher = teacher;
        this.grades = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public int getId() {
        return id;
    }
}
