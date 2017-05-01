package com.dariuszpaluch.models;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Calendar;

@XmlRootElement
class Teacher extends Person {

    public Teacher(String name, String surname, Calendar dateOfBirth) {
        super(name, surname, dateOfBirth);
    }

    public Teacher() {
    }
}
