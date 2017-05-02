package com.dariuszpaluch.service;

import com.dariuszpaluch.dao.Context;
import com.dariuszpaluch.dao.interfaces.IGradeDao;
import com.dariuszpaluch.exception.DataNotFoundException;
import com.dariuszpaluch.models.Course;
import com.dariuszpaluch.models.Grade;
import com.dariuszpaluch.models.Student;

import java.util.List;

public class GradeService implements IGradeDao{
    private final Context context = Context.getInstance();

    @Override
    public List<Grade> getAllGrades() {
        return this.context.getGrades().getAllGrades();
    }

    @Override
    public List<Grade> getStudentGrade(String studentIndex) {
        Student student = this.context.getStudents().getStudent(studentIndex);

        return this.context.getGrades().getStudentGrade(student.getIndex());
    }

    @Override
    public List<Grade> getCourseGrade(int courseId) {
        Course course = this.context.getCourses().getCourse(courseId);

        return this.context.getGrades().getCourseGrade(course.getId());
    }

    @Override
    public Grade getGrade(int id) {
        Grade grade = this.context.getGrades().getGrade(id);

        if(grade == null) {
            throw new DataNotFoundException("Grade with id " + id + " not found");
        }

        return grade;
    }

    @Override
    public boolean updateGrade(Grade grade, int id) {
        Course course = this.context.getCourses().getCourse(grade.getCourseId());
        Student student = this.context.getStudents().getStudent(grade.getStudentIndex());

        boolean result = this.context.getGrades().updateGrade(grade, id);
        if(!result) {
            throw new DataNotFoundException("Student with i " + id + " not found");
        }
        return true;
    }

    @Override
    public boolean deleteGrade(int id) {
        boolean result = this.context.getGrades().deleteGrade(id);

        if(!result) {
            throw new DataNotFoundException("Grade with id " + id + " not found");
        }

        return true;

    }

    @Override
    public Grade addGrade(Grade grade) {
        Course course = this.context.getCourses().getCourse(grade.getCourseId());
        Student student = this.context.getStudents().getStudent(grade.getStudentIndex());

        return this.context.getGrades().addGrade(grade);
    }
}
