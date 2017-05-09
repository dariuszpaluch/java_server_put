package com.dariuszpaluch.dao;

import com.dariuszpaluch.dao.interfaces.ICourseDao;
import com.dariuszpaluch.models.Course;

import java.util.ArrayList;
import java.util.List;

public class CourseDaoImpl implements ICourseDao {

    private List<Course> courses;

    public CourseDaoImpl() {
        this.courses = new ArrayList<>();
    }

    public CourseDaoImpl(List<Course> courses) {
        this.courses = courses;
    }

    @Override
    public List<Course> getAllCourse() {
        return courses;
    }

    @Override
    public Course getCourse(int id) {
        for(Course item: courses) {
            if(item.getId() == id) {
                return item;
            }
        }

        return null;
    }

    @Override
    public boolean updateCourse(Course course, int id) {
        for(Course item: courses) {
            if(item.getId() == id) {
                item.setName(course.getName());
                item.setTeacher(course.getTeacher());
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean deleteCourse(int id) {
        for(Course item: courses) {
            if(item.getId() == id) {
                courses.remove(item);
                return true;
            }
        }

        return false;
    }

    @Override
    public Course addCourse(Course course) {
        courses.add(course);

        return this.getCourse(course.getId());
    }
}
