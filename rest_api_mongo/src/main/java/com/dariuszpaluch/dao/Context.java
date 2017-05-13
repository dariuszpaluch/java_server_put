package com.dariuszpaluch.dao;

import com.dariuszpaluch.dao.interfaces.ICourseDao;
import com.dariuszpaluch.dao.interfaces.IGradeDao;
import com.dariuszpaluch.dao.interfaces.IStudentDao;
import com.dariuszpaluch.models.Counter;
import com.dariuszpaluch.models.Course;
import com.dariuszpaluch.models.Grade;
import com.dariuszpaluch.models.Student;
import com.mongodb.MongoClient;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

import java.util.ArrayList;
import java.util.Calendar;
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
  }

  public void initialize() {
    CounterDao counterDao = new CounterDao();

    if (datastore.getCount(Student.class) == 0) {
      Counter counter = new Counter("students");
      datastore.save(counter);

      List<Student> dataStudents = new ArrayList<Student>();
      Calendar calendar = Calendar.getInstance();
      calendar.set(Calendar.YEAR, 1994);
      calendar.set(Calendar.MONTH, 1);
      calendar.set(Calendar.DAY_OF_MONTH, 18);
      Student student1 = new Student(counterDao.getSeq("students"), "Dariusz", "Paluch", calendar.getTime());

      calendar.set(Calendar.YEAR, 2000);
      calendar.set(Calendar.MONTH, 5);
      calendar.set(Calendar.DAY_OF_MONTH, 20);
      Student student2 = new Student(counterDao.getSeq("students"), "Adam", "Nowak", calendar.getTime());
      dataStudents.add(student1);
      dataStudents.add(student2);

      datastore.save(dataStudents);
    }

    if (datastore.getCount(Course.class) == 0) {
      List<Course> dataCourses = new ArrayList<Course>();
      Course course1 = new Course("Analiza Matematyczna", "J. Węglarz");
      Course course2 = new Course("Matematyka Dyskretna", "M. Nowak");
      dataCourses.add(course1);
      dataCourses.add(course2);
      datastore.save(dataCourses);
    }

    if (datastore.getCount(Grade.class) == 0) {
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
