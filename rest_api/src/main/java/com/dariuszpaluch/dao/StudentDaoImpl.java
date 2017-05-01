package com.dariuszpaluch.dao;

import com.dariuszpaluch.dao.interfaces.IStudentDao;
import com.dariuszpaluch.models.Student;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class StudentDaoImpl implements IStudentDao {

    private static final List<Student> students;

    static {
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
    public Student getStudent(String index) {
        for(Student item : students) {
            if(item.getIndex().equals(index)) {
                return item;
            }
        }

        return null;
    }

    @Override
    public boolean updateStudent(Student student, String index) {
        for(Student item: students) {
            if(item.getIndex().equals(index)) {
                item.setName(student.getName());
                item.setDateOfBirth(student.getDateOfBirth());
                item.setSurname(student.getSurname());
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean deleteStudent(String index) {
        for(Student item : students) {
            if(item.getIndex().equals(index)) {
                students.remove(item);
                return true;
            }
        }

        return  false;
    }

    @Override
    public Student addStudent(Student student) {
        students.add(student);

        return this.getStudent(student.getIndex());
    }
}
