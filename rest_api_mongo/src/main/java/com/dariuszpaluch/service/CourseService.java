package com.dariuszpaluch.service;

import com.dariuszpaluch.dao.Context;
import com.dariuszpaluch.dao.interfaces.ICourseDao;
import com.dariuszpaluch.exception.DataNotFoundException;
import com.dariuszpaluch.models.Course;

import java.util.List;

public class CourseService implements ICourseDao {
    private final Context context = Context.getInstance();

    @Override
    public List<Course> getAllCourse() {
        return this.context.getCourses().getAllCourse();
    }

    @Override
    public Course getCourse(int id) {
        Course course = this.context.getCourses().getCourse(id);

        if(course == null) {
            throw new DataNotFoundException("Course with id " + id + " not found");
        }

        return course;
    }

    @Override
    public boolean updateCourse(Course course, int id) {
        boolean result = this.context.getCourses().updateCourse(course, id);

        if(!result) {
            throw new DataNotFoundException("Course with id " + id + " not found");
        }

        return true;
    }

    @Override
    public boolean deleteCourse(int id) {
        boolean result = this.context.getCourses().deleteCourse(id);

        if(!result) {
            throw new DataNotFoundException("Course with id " + id + " not found");
        }

        return true;
    }

    @Override
    public Course addCourse(Course course) {
        return this.context.getCourses().addCourse(course);
    }
}
