package com.dariuszpaluch.dao;

import com.dariuszpaluch.dao.interfaces.IGradeDao;
import com.dariuszpaluch.models.Course;
import com.dariuszpaluch.models.Grade;
import com.dariuszpaluch.models.Student;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class GradeDaoImpl implements IGradeDao {
  private Datastore datastore = Context.getInstance().getDatastore();

  @Override
  public List<Grade> getAllGrades() {
    return datastore.find(Grade.class).asList();
  }

  @Override
  public List<Grade> getStudentGrade(Student student, Course course, int compareType, int compareValue, String courseName, Date created ) {
    Query<Grade> query = this.datastore.createQuery(Grade.class).filter("student", student);

    if(course != null ) {
      query.filter("course", course);
    }

    if(compareValue > 0) {
      switch (compareType) {
        case -1:
          query.filter("value <", compareValue);
          break;
        case 0:
          query.filter("value ==", compareValue);
          break;
        case 1:
          query.filter("value >", compareValue);
          break;
      }
    }

    if(created != null) {
      query.filter("created", created);
    }






    List<Grade> list = null;
    try {
      list = query.asList();
    }
    catch(Exception e) {
      list = new ArrayList<>();
    }


    if(courseName != null) {
      List<Grade> result = new ArrayList<>();

      list = list.stream().filter(_grade -> _grade.getCourse().getName().toLowerCase().contains(courseName.toLowerCase())).collect(Collectors.toList());
    }

    return list;
  }

  @Override
  public List<Grade> getCourseGrade(Course course) {
    return this.datastore.createQuery(Grade.class).filter("course", course).asList();
  }

  @Override
  public Grade getGrade(String id) {
    if (!ObjectId.isValid(id)) {
      return null;
    }

    return this.datastore.get(Grade.class, new ObjectId(id));
  }

  @Override
  public boolean updateGrade(Grade grade, String id) {
    Grade findGrade = this.getGrade(id);

    if (findGrade == null) {
      return false;
    }
    findGrade.setCourse(grade.getCourse());
    findGrade.setStudent(grade.getStudent());
    findGrade.setCreated(grade.getCreated());
    findGrade.setValue(grade.getValue());

    this.datastore.save(findGrade);
    return true;
  }

  @Override
  public boolean deleteGrade(String id) {
    Grade findGrade = this.getGrade(id);

    if (findGrade == null) {
      return false;
    }

    this.datastore.delete(findGrade);
    return true;
  }

  public boolean deleteGradesByCourse(Course course) {
    List<Grade> grades = this.getCourseGrade(course);
    for(Grade grade: grades) {
      this.datastore.delete(grade);
    }
    return true;
  }

  @Override
  public Grade addGrade(Grade grade) {
    this.datastore.save(grade);

    return this.getGrade(grade.getId().toString());
  }
}
