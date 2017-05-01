package com.dariuszpaluch.models;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Calendar;

@XmlRootElement
class Grade {
    @NotNull
    private Double value;
    @NotNull
    private Calendar created;
    @NotNull
    private Course course;

    public Grade() {
    }

    public Grade(Double value, Calendar created, Course course) {
        this.value = value;
        this.created = created;
        this.course = course;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public Calendar getCreated() {
        return created;
    }

    public void setCreated(Calendar created) {
        this.created = created;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
}
