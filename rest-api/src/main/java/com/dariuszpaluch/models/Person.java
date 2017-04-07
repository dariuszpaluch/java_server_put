package com.dariuszpaluch.models;

import java.util.Calendar;

class Person {
    private String name;
    private String surname;
    private Calendar date;

    Person(String name, String surname, Calendar date) {
        this.name = name;
        this.surname = surname;
        this.date = date;
    }
}

