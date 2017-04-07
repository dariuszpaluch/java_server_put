package com.dariuszpaluch.models;

import java.util.*;

class Student extends Person{
    private String index;
    private Set<Grade> grades;

    public Student(String name, String surname, Calendar date) {
        super(name, surname, date);
        this.grades = new HashSet<Grade>();
    }

    public void addGrade(Double value, Calendar created, Course course) {
        Grade grade = new Grade(value, created, course);
        grades.add(grade);
    }


}
