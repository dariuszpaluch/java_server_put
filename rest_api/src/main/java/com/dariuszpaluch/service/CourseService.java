package com.dariuszpaluch.service;

import com.dariuszpaluch.dao.CourseDaoImpl;
import com.dariuszpaluch.dao.StudentDaoImpl;
import com.dariuszpaluch.dao.interfaces.ICourseDao;
import com.dariuszpaluch.dao.interfaces.IStudentDao;
import com.dariuszpaluch.exception.DataNotFoundException;
import com.dariuszpaluch.models.Course;
import com.dariuszpaluch.models.Student;

import java.util.List;

public class CourseService implements ICourseDao {
    private ICourseDao courses;

    public CourseService() {
        this.courses = new CourseDaoImpl();
    }

    @Override
    public List<Course> getAllCourse() {
        return courses.getAllCourse();
    }

    @Override
    public Course getCourse(int id) {
        Course course = courses.getCourse(id);

        if(course == null) {
            throw new DataNotFoundException("Course with id " + id + " not found");
        }

        return course;
    }

    @Override
    public boolean updateCourse(Course course, int id) {
        boolean result = courses.updateCourse(course, id);

        if(!result) {
            throw new DataNotFoundException("Course with id " + id + " not found");
        }

        return true;
    }

    @Override
    public boolean deleteCourse(int id) {
        boolean result = courses.deleteCourse(id);

        if(!result) {
            throw new DataNotFoundException("Student with id " + id + " not found");
        }

        return true;
    }

    @Override
    public Course addCourse(Course course) {
        return courses.addCourse(course);
    }
}
