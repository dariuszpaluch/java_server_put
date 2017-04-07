package com.dariuszpaluch.models;

import java.util.Calendar;

class Grade {
    private Double value;
    private Calendar created;
    private Course course;

    Grade(Double value, Calendar created, Course course) {
        this.value = value;
        this.created = created;
        this.course = course;
    }
}
