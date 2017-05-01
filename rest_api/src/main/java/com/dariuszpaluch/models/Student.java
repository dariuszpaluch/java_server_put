package com.dariuszpaluch.models;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.*;

@XmlRootElement
class Student extends Person{
    @NotNull
    private String index;
    @NotNull
    private Set<Grade> grades;

    public Student() {
    }

    public Student(String name, String surname, Calendar dateOfBirth, String index) {
        super(name, surname, dateOfBirth);
        this.index = index;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public Set<Grade> getGrades() {
        return grades;
    }

    public void setGrades(Set<Grade> grades) {
        this.grades = grades;
    }
}
