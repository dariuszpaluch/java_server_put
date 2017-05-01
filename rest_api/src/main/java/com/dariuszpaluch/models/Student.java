package com.dariuszpaluch.models;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.*;

@XmlRootElement
public class Student extends Person{
    @NotNull
    private String index;
//    @Override
//    public Map<String, String> unmarshal(StrStrMyMap v) throws Exception {
//        Map<String, String> map = new HashMap<String,String>();
//        for (Iterator<StrStrKeyVal> it = v.map.iterator(); it.hasNext();) {
//            StrStrKeyVal strStr = it.next();
//            map.put(strStr.key, strStr.value);
//        }
//        return map;
//    }

//    private Set<Grade> grades;

    public Student() {
    }

    public Student(String name, String surname, Date dateOfBirth, String index) {
        super(name, surname, dateOfBirth);
        this.index = index;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

//    public Set<Grade> getGrades() {
//        return grades;
//    }
//
//    public void setGrades(Set<Grade> grades) {
//        this.grades = grades;
//    }
}
