package com.dariuszpaluch.dao;

import com.dariuszpaluch.dao.interfaces.IStudentDao;
import com.dariuszpaluch.models.Student;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class StudentDaoImpl implements IStudentDao {

    List<Student> students;

    public StudentDaoImpl() {
        students = new ArrayList<Student>();
        Student student1 = new Student("Dariusz", "Paluch", new Date(), "123456789");
        Student student2 = new Student("Adam", "Nowak", new Date(), "123456789");
        students.add(student1);
        students.add(student2);
    }

    @Override
    public List<Student> getAllStudents() {
        return this.students;
    }

    @Override
    public Student getStudent(int index) {
        return null;
    }

    @Override
    public void updateStudent(Student student) {

    }

    @Override
    public void deleteStudent(Student student) {

    }
}
