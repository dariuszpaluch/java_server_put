package com.dariuszpaluch.dao.interfaces;

import com.dariuszpaluch.models.Course;
import com.dariuszpaluch.models.Grade;

import java.util.List;

public interface IGradeDao {
    public List<Grade> getAllGrades();
    public List<Grade> getStudentGrade(String studentIndex);
    public List<Grade> getCourseGrade(int courseId);
    public Grade getGrade(int id);
    public boolean updateGrade(Grade grade, int id);
    public boolean deleteGrade(int id);
    public Grade addGrade(Grade grade);
}
