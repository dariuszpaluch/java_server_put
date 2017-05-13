package com.dariuszpaluch.dao;

import com.dariuszpaluch.dao.interfaces.IStudentDao;
import com.dariuszpaluch.models.Course;
import com.dariuszpaluch.models.Student;
import com.dariuszpaluch.utils.DatastoreHandlerUtil;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class StudentDaoImpl implements IStudentDao {
  private Datastore datastore = Context.getInstance().getDatastore();

  @Override
  public List<Student> getAllStudents(String firstName, String lastName, Date dateOfBirth, int dateOfBirthCompareType) {
    Query<Student> query = this.datastore.createQuery(Student.class);
      System.out.println(firstName);

    if(firstName != null ) {
      query = query.field("firstName").containsIgnoreCase(firstName);
    }

    if(lastName != null ) {
      query = query.field("lastName").containsIgnoreCase(lastName);
    }

    if (dateOfBirth != null) {
      switch (dateOfBirthCompareType) {
        case 0:
          query.filter("dateOfBirth <", dateOfBirth);
          break;
        case 1:
          query.filter("dateOfBirth ==", dateOfBirth);
          break;
        case 2:
          query.filter("dateOfBirth >", dateOfBirth);
          break;
      }
    }



    return query.asList();
  }

  @Override
  public Student getStudent(int index) {
    Query<Student> query = this.datastore.createQuery(Student.class);
    query.filter("index ==", index);

    List<Student> students = query.asList();
    if(students.size() > 0) {
      return students.get(0);
    }

    return null;
  }

  @Override
  public boolean updateStudent(Student student, int index) {
    Student findStudent = this.getStudent(index);

    if (findStudent == null) {
      return false;
    }

    findStudent.setDateOfBirth(student.getDateOfBirth());
    findStudent.setFirstName(student.getFirstName());
    findStudent.setLastName(student.getLastName());

    this.datastore.save(findStudent);
    return true;
  }

  @Override
  public boolean deleteStudent(int index) {
    Student findStudent = this.getStudent(index);
    if (findStudent == null) {
      return false;
    }

    this.datastore.delete(findStudent);
    return true;
  }

  @Override
  public Student addStudent(Student student) {
    this.datastore.save(student);

    return student;
  }
}
