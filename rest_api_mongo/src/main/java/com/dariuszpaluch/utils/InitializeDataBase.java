package com.dariuszpaluch.utils;

import com.dariuszpaluch.models.Course;
import com.dariuszpaluch.models.Student;
import org.mongodb.morphia.Datastore;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class InitializeDataBase {
  private static List<Student> students = new ArrayList<>();
  private static List<Course> courses = new ArrayList<>();

  public static void initializeStudents() throws ParseException {
    Datastore datastore = DatastoreHandlerUtil.getInstance().getDatastore();

    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    students.add(new Student("Dariusz", "Paluch", formatter.parse("1920-03-24")));
    students.add(new Student("Adam", "Nowak", formatter.parse("1940-05-02")));
    students.add(new Student("Nowak", "Przemek", formatter.parse("1994-05-13")));
    datastore.save(students);
  }
}
