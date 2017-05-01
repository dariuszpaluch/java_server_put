package com.dariuszpaluch.dao.interfaces;

import com.dariuszpaluch.models.Course;
import com.dariuszpaluch.models.Student;

import java.util.List;

public interface ICourse {
    public List<Student> getAllCourse();
    public Student getCourse(int index);
    public void updateCourse(Course course);
    public void deleteCourse(Course course);
}