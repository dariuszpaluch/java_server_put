package com.dariuszpaluch.dao;

import com.dariuszpaluch.dao.interfaces.ICourseDao;
import com.dariuszpaluch.dao.interfaces.IGradeDao;
import com.dariuszpaluch.dao.interfaces.IStudentDao;
import com.dariuszpaluch.models.Course;
import com.dariuszpaluch.models.Grade;
import com.dariuszpaluch.models.Student;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Context {
    private static Context instance;
    private ICourseDao courses;
    private IGradeDao grades;
    private IStudentDao students;

    private Context() {
        List<Course> dataCourses = new ArrayList<Course>();
        Course course1 = new Course("Analiza Matematyczna", "J. Węglarz");
        Course course2 = new Course("Matematyka Dyskretna", "M. Nowak");
        dataCourses.add(course1);
        dataCourses.add(course2);

        List<Student> dataStudents = new ArrayList<Student>();
        Student student1 = new Student("Dariusz", "Paluch", "10-03-1994");
        Student student2 = new Student("Adam", "Nowak", "08-03-1992");
        dataStudents.add(student1);
        dataStudents.add(student2);

        List<Grade> dataGrades = new ArrayList<Grade>();
        Grade grade1 = new Grade(2.5, "05-03-2015", course1.getId(), student1.getIndex());
        Grade grade2 = new Grade(5.0, "05-05-2015", course2.getId(), student1.getIndex());
        Grade grade3 = new Grade(4.5, "05-08-2015", course1.getId(), student2.getIndex());
        dataGrades.add(grade1);
        dataGrades.add(grade2);
        dataGrades.add(grade3);

        courses = new CourseDaoImpl(dataCourses);
        grades = new GradeDaoImpl(dataGrades);
        students = new StudentDaoImpl(dataStudents);
        System.out.println(dataStudents.size());
    }

    public static Context getInstance()
    {
        if(instance == null) {
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
