package com.dariuszpaluch.dao.interfaces;

import com.dariuszpaluch.models.Course;
import com.dariuszpaluch.models.Student;

import java.util.List;

public interface ICourseDao {
    public List<Course> getAllCourse();
    public Course getCourse(int id);
    public boolean updateCourse(Course course, int id);
    public boolean deleteCourse(int id);
    public Course addCourse(Course course);
}