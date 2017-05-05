package com.dariuszpaluch.dao;

import com.dariuszpaluch.dao.interfaces.IStudentDao;
import com.dariuszpaluch.models.Student;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class StudentDaoImpl implements IStudentDao {

    private List<Student> students;

    public StudentDaoImpl() {
        students = new ArrayList<Student>();
    }

    public StudentDaoImpl(List<Student> students) {
        this.students = students;
    }

    @Override
    public List<Student> getAllStudents() {
        return this.students;
    }

    @Override
    public Student getStudent(int index) {
        for(Student item : students) {
            if(item.getIndex() == index) {
                return item;
            }
        }

        return null;
    }

    @Override
    public boolean updateStudent(Student student, int index) {
        for(Student item: students) {
            if(item.getIndex() == index) {
                item.setName(student.getName());
                item.setDateOfBirth(student.getDateOfBirth());
                item.setSurname(student.getSurname());
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean deleteStudent(int index) {
        for(Student item : students) {
            if(item.getIndex() == index) {
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
