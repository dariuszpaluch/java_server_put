package com.dariuszpaluch.dao;

import com.dariuszpaluch.dao.interfaces.ICourseDao;
import com.dariuszpaluch.dao.interfaces.IGradeDao;
import com.dariuszpaluch.dao.interfaces.IStudentDao;
import com.mongodb.MongoClient;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

public class Context {
  private static Context instance;
  private ICourseDao courses;
  private IGradeDao grades;
  private IStudentDao students;

  private Context() {
//        List<Course> dataCourses = new ArrayList<Course>();
//        Course course1 = new Course("Analiza Matematyczna", "J. Węglarz");
//        Course course2 = new Course("Matematyka Dyskretna", "M. Nowak");
//        dataCourses.add(course1);
//        dataCourses.add(course2);
//
//        List<Student> dataStudents = new ArrayList<Student>();
//        Student student1 = new Student("Dariusz", "Paluch", new Date());
//        Student student2 = new Student("Adam", "Nowak", new Date());
//        dataStudents.add(student1);
//        dataStudents.add(student2);
//
//        List<Grade> dataGrades = new ArrayList<Grade>();
//        Grade grade1 = new Grade(2.5, new Date(), course1.getId(), student1.getIndex());
//        Grade grade2 = new Grade(5.0, new Date(), course2.getId(), student1.getIndex());
//        Grade grade3 = new Grade(4.5, new Date(), course1.getId(), student2.getIndex());
//        dataGrades.add(grade1);
//        dataGrades.add(grade2);
//        dataGrades.add(grade3);
//
        courses = new CourseDaoImpl();
        grades = new GradeDaoImpl();
        students = new StudentDaoImpl();

    final Morphia morphia = new Morphia();
    morphia.mapPackage("com.dariuszpaluch.models");
    final Datastore datastore = morphia.createDatastore(new MongoClient(), "morphia_example");
    datastore.ensureIndexes();

  }

  public static Context getInstance() {
    if (instance == null) {
      instance = new Context();
    }

    return instance;
  }

  public ICourseDao getCourses() {
    return courses;
  }

  public IGradeDao getGrades() {
    return grades;
  }

  public IStudentDao getStudents() {
    return students;
  }
}
