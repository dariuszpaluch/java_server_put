package com.dariuszpaluch.dao;

import com.dariuszpaluch.dao.interfaces.ICourseDao;
import com.dariuszpaluch.models.Course;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;

import java.util.ArrayList;
import java.util.List;

public class CourseDaoImpl implements ICourseDao {
  private Datastore datastore = Context.getInstance().getDatastore();

  @Override
  public List<Course> getAllCourse() {
    return datastore.find(Course.class).asList();

  }

  @Override
  public Course getCourse(String id) {
    return this.datastore.get(Course.class, new ObjectId(id));
  }


  @Override
  public boolean updateCourse(Course course, String id) {
    Course findCourse = this.datastore.get(Course.class, new ObjectId(id));

    if (findCourse == null) {
      return false;
    }

    findCourse.setName(course.getName());
    findCourse.setTeacher(course.getTeacher());
    this.datastore.save(findCourse);
    return true;
  }

  @Override
  public boolean deleteCourse(String id) {
    Course findCourse = this.datastore.get(Course.class, new ObjectId(id));
    if (findCourse == null) {
      return false;
    }
    this.datastore.delete(Course.class, new ObjectId(id));

    return true;
  }

  @Override
  public Course addCourse(Course course) {
    this.datastore.save(course);

    return course;
  }
}
