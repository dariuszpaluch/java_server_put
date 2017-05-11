package com.dariuszpaluch.dao.interfaces;

import com.dariuszpaluch.models.Course;
import com.dariuszpaluch.models.Grade;
import com.dariuszpaluch.models.Student;

import java.util.List;

public interface IGradeDao {
    public List<Grade> getAllGrades();
    public List<Grade> getStudentGrade(Student student);
    public List<Grade> getCourseGrade(Course course);
    public Grade getGrade(String id);
    public boolean updateGrade(Grade grade, String id);
    public boolean deleteGrade(String id);
    public Grade addGrade(Grade grade);
}
