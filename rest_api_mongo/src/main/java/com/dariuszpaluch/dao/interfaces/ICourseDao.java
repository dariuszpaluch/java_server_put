package com.dariuszpaluch.dao.interfaces;

import com.dariuszpaluch.models.Course;
import com.dariuszpaluch.models.Student;

import java.util.List;

public interface ICourseDao {
    public List<Course> getAllCourse(String name, String teacher);
    public Course getCourse(String id);
    public boolean updateCourse(Course course, String id);
    public boolean deleteCourse(String id);
    public Course addCourse(Course course);
}