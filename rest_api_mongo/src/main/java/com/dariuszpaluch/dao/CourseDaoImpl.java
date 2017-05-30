package com.dariuszpaluch.dao;

import com.dariuszpaluch.dao.interfaces.ICourseDao;
import com.dariuszpaluch.models.Course;
import com.dariuszpaluch.models.Student;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;

import java.util.ArrayList;
import java.util.List;

public class CourseDaoImpl implements ICourseDao {
  private Datastore datastore = Context.getInstance().getDatastore();

  @Override
  public List<Course> getAllCourse(String teacher) {
    Query<Course> query = this.datastore.createQuery(Course.class);

    if(teacher != null ) {
      query.filter("teacher ==", teacher);
    }

    return query.asList();

  }

  @Override
  public Course getCourse(String id) {
    if (!ObjectId.isValid(id)) {
      return null;
    }

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
    this.datastore.delete(findCourse);

    return true;
  }

  @Override
  public Course addCourse(Course course) {
    this.datastore.save(course);

    return course;
  }
}
