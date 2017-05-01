package com.dariuszpaluch.models;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Calendar;
import java.util.Date;

@XmlRootElement
class Teacher extends Person {

    public Teacher(String name, String surname, Date dateOfBirth) {
        super(name, surname, dateOfBirth);
    }

    public Teacher() {
    }
}
