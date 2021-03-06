package com.dariuszpaluch.service;

import com.dariuszpaluch.dao.Context;
import com.dariuszpaluch.dao.CourseDaoImpl;
import com.dariuszpaluch.dao.GradeDaoImpl;
import com.dariuszpaluch.dao.interfaces.ICourseDao;
import com.dariuszpaluch.exception.DataNotFoundException;
import com.dariuszpaluch.models.Course;

import java.util.List;

public class CourseService implements ICourseDao{
    private final CourseDaoImpl coursesDao = new CourseDaoImpl();
    private final GradeDaoImpl gradeDao = new GradeDaoImpl();

    @Override
    public List<Course> getAllCourse(String name, String teacher) {
        return this.coursesDao.getAllCourse(name, teacher);
    }

    @Override
    public Course getCourse(String id) {
        Course course = this.coursesDao.getCourse(id);

        if(course == null) {
            throw new DataNotFoundException("Course with id " + id + " not found");
        }

        return course;
    }

    @Override
    public boolean updateCourse(Course course, String id) {
        boolean result = this.coursesDao.updateCourse(course, id);

        if(!result) {
            throw new DataNotFoundException("Course with id " + id + " not found");
        }

        return true;
    }

    @Override
    public boolean deleteCourse(String id) {
        Course course = this.coursesDao.getCourse(id);

        if(course == null) {
            throw new DataNotFoundException("Course with id " + id + " not found");
        }

        this.gradeDao.deleteGradesByCourse(course);
        this.coursesDao.deleteCourse(id);

        return true;
    }

    @Override
    public Course addCourse(Course course) {
        return this.coursesDao.addCourse(course);
    }
}
