package com.dariuszpaluch.dao;

import com.dariuszpaluch.dao.interfaces.ICourseDao;
import com.dariuszpaluch.models.Course;
import org.mongodb.morphia.Datastore;

import java.util.ArrayList;
import java.util.List;

public class CourseDaoImpl{
    private Datastore datastore = Context.getInstance().getDatastore();

//    @Override
    public List<Course> getAllCourse() {
        return datastore.find(Course.class).asList();

    }

//    @Override
//    public Course getCourse(int id) {
//        for(Course item: courses) {
//            if(item.getId() == id) {
//                return item;
//            }
//        }
//
//        return null;
//    }
//
//    @Override
//    public boolean updateCourse(Course course, int id) {
//        for(Course item: courses) {
//            if(item.getId() == id) {
//                item.setName(course.getName());
//                item.setTeacher(course.getTeacher());
//                return true;
//            }
//        }
//
//        return false;
//    }
//
//    @Override
//    public boolean deleteCourse(int id) {
//        for(Course item: courses) {
//            if(item.getId() == id) {
//                courses.remove(item);
//                return true;
//            }
//        }
//
//        return false;
//    }
//
//    @Override
//    public Course addCourse(Course course) {
//        courses.add(course);
//
//        return this.getCourse(course.getId());
//    }
}
