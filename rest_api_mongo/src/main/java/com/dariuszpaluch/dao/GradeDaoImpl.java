package com.dariuszpaluch.dao;

import com.dariuszpaluch.dao.interfaces.IGradeDao;
import com.dariuszpaluch.models.Course;
import com.dariuszpaluch.models.Grade;
import com.dariuszpaluch.models.Student;

import java.util.ArrayList;
import java.util.List;

public class GradeDaoImpl{
//    private List<Grade> grades;
//
//    public GradeDaoImpl() {
//        grades = new ArrayList<>();
//    }
//
//    public GradeDaoImpl(List<Grade> grades) {
//        this.grades = grades;
//    }
//
//    @Override
//    public List<Grade> getAllGrades() {
//        return grades;
//    }
//
//    @Override
//    public List<Grade> getStudentGrade(int studentIndex) {
//        List<Grade> studentGrades = new ArrayList<>();
//
//        for(Grade item: grades) {
//            if(item.getStudentIndex() == studentIndex) {
//                studentGrades.add(item);
//            }
//        }
//
//        return studentGrades;
//    }
//
//    @Override
//    public List<Grade> getCourseGrade(int courseId) {
//        List<Grade> studentGrades = new ArrayList<>();
//
//        for(Grade item: grades) {
//            if(item.getCourseId() == courseId) {
//                studentGrades.add(item);
//            }
//        }
//
//        return studentGrades;
//    }
//
//    @Override
//    public Grade getGrade(int id) {
//        for(Grade item : grades) {
//            if(item.getId() == id) {
//                return item;
//            }
//        }
//
//        return null;
//    }
//
//    @Override
//    public boolean updateGrade(Grade grade, int id) {
//        for(Grade item: grades) {
//            if(item.getId() == id) {
//                item.setCourseId(grade.getCourseId());
//                item.setStudentIndex(grade.getStudentIndex());
//                item.setCreated(grade.getCreated());
//                item.setValue(grade.getValue());
//            }
//        }
//
//        return false;
//    }
//
//    @Override
//    public boolean deleteGrade(int id) {
//        for(Grade item : grades) {
//            if(item.getId() == id) {
//                grades.remove(item);
//                return true;
//            }
//        }
//
//        return  false;
//    }
//
//    @Override
//    public Grade addGrade(Grade grade) {
//        grades.add(grade);
//
//        return this.getGrade(grade.getId());
//    }
}
