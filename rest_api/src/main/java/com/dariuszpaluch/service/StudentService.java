package com.dariuszpaluch.service;

import com.dariuszpaluch.dao.StudentDaoImpl;
import com.dariuszpaluch.dao.interfaces.IStudentDao;
import com.dariuszpaluch.exception.DataNotFoundException;
import com.dariuszpaluch.models.Student;

import javax.ws.rs.NotFoundException;
import java.util.List;

public class StudentService implements IStudentDao {
    private IStudentDao students;

    public StudentService() {
        this.students = new StudentDaoImpl();
    }

    @Override
    public List<Student> getAllStudents() {
        return students.getAllStudents();
    }

    @Override
    public Student getStudent(String index) {
        Student student = students.getStudent(index);

        if(student == null) {
            throw new DataNotFoundException("Student with index " + index + " not found");
        }

        return student;
    }

    @Override
    public boolean updateStudent(Student student, String index) {
        boolean result = students.updateStudent(student, index);

        if(!result) {
            throw new DataNotFoundException("Student with index " + index + " not found");
        }

        return true;
    }

    @Override
    public boolean deleteStudent(String index) {
        boolean result = students.deleteStudent(index);

        if(!result) {
            throw new DataNotFoundException("Student with index " + index + " not found");
        }

        return true;
    }

    @Override
    public Student addStudent(Student student) {
        return students.addStudent(student);
    }

}
