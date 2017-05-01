package com.dariuszpaluch.dao;

import com.dariuszpaluch.dao.interfaces.IGradeDao;
import com.dariuszpaluch.models.Course;
import com.dariuszpaluch.models.Grade;
import com.dariuszpaluch.models.Student;

import java.util.ArrayList;
import java.util.List;

public class GradeDaoImpl implements IGradeDao{
    private static final List<Grade> grades;

    static {
        grades = new ArrayList<>();
    }

    @Override
    public List<Grade> getAllGrades() {
        return grades;
    }

    @Override
    public Grade getGrade(int id) {
        for(Grade item : grades) {
            if(item.getId() == id) {
                return item;
            }
        }

        return null;
    }

    @Override
    public boolean updateGrade(Grade grade, int id) {
        for(Grade item: grades) {
            if(item.getId() == id) {
                item.setCourse(grade.getCourse());
                item.setCreated(grade.getCreated());
                item.setValue(grade.getValue());
            }
        }

        return false;
    }

    @Override
    public boolean deleteGrade(int id) {
        for(Grade item : grades) {
            if(item.getId() == id) {
                grades.remove(item);
                return true;
            }
        }

        return  false;
    }

    @Override
    public Grade addGrade(Grade grade) {
        grades.add(grade);

        return this.getGrade(grade.getId());
    }
}
