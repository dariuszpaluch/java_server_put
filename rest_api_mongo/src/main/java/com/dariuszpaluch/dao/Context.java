package com.dariuszpaluch.dao;

import com.dariuszpaluch.dao.interfaces.ICourseDao;
import com.dariuszpaluch.dao.interfaces.IGradeDao;
import com.dariuszpaluch.dao.interfaces.IStudentDao;
import com.dariuszpaluch.models.Course;
import com.dariuszpaluch.models.Grade;
import com.dariuszpaluch.models.Student;
import com.mongodb.MongoClient;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Context {
  private static Context instance;
//  private ICourseDao courses;
//  private IGradeDao grades;
//  private IStudentDao students;
  private final Datastore datastore;

  public Context() {
//    courses = new CourseDaoImpl();
//    grades = new GradeDaoImpl();
//    students = new StudentDaoImpl();

    final Morphia morphia = new Morphia();
    morphia.mapPackage("com.dariuszpaluch.models");
    datastore = morphia.createDatastore(new MongoClient("localhost", 8004), "morphia_example");
    datastore.ensureIndexes();


    if(datastore.getCount(Student.class) == 0) {
      List<Student> dataStudents = new ArrayList<Student>();
      Student student1 = new Student("Dariusz", "Paluch", new Date());
      Student student2 = new Student("Adam", "Nowak", new Date());
      dataStudents.add(student1);
      dataStudents.add(student2);

      datastore.save(dataStudents);
    }

    if(datastore.getCount(Course.class) == 0) {
      List<Course> dataCourses = new ArrayList<Course>();
      Course course1 = new Course("Analiza Matematyczna", "J. Węglarz");
      Course course2 = new Course("Matematyka Dyskretna", "M. Nowak");
      dataCourses.add(course1);
      dataCourses.add(course2);
      datastore.save(dataCourses);
    }

    if(datastore.getCount(Grade.class) == 0) {
      List<Grade> dataGrades = new ArrayList<Grade>();
      Grade grade1 = new Grade(2.5, new Date(), datastore.find(Course.class).asList().get(0), datastore.find(Student.class).asList().get(0));
      Grade grade2 = new Grade(5.0, new Date(), datastore.find(Course.class).asList().get(0), datastore.find(Student.class).asList().get(0));
      Grade grade3 = new Grade(4.5, new Date(), datastore.find(Course.class).asList().get(0), datastore.find(Student.class).asList().get(0));
      dataGrades.add(grade1);
      dataGrades.add(grade2);
      dataGrades.add(grade3);

      datastore.save(dataGrades);
    }
  }

  public static Context getInstance() {
    if (instance == null) {
      instance = new Context();
    }

    return instance;
  }

//  public ICourseDao getCourses() {
//    return courses;
//  }
//
//  public IGradeDao getGrades() {
//    return grades;
//  }
//
//  public IStudentDao getStudents() {
//    return students;
//  }

  public Datastore getDatastore() {
    return datastore;
  }
}
