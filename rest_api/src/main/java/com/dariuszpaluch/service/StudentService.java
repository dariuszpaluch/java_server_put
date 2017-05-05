package com.dariuszpaluch.service;

import com.dariuszpaluch.dao.Context;
import com.dariuszpaluch.dao.interfaces.IStudentDao;
import com.dariuszpaluch.exception.DataNotFoundException;
import com.dariuszpaluch.models.Student;

import java.util.List;

public class StudentService implements IStudentDao {
    private final Context context = Context.getInstance();

    @Override
    public List<Student> getAllStudents() {
        return this.context.getStudents().getAllStudents();
    }

    @Override
    public Student getStudent(int index) {
        Student student = this.context.getStudents().getStudent(index);

        if(student == null) {
            throw new DataNotFoundException("Student with index " + index + " not found");
        }

        return student;
    }

    @Override
    public boolean updateStudent(Student student, int index) {
        boolean result = this.context.getStudents().updateStudent(student, index);

        if(!result) {
            throw new DataNotFoundException("Student with index " + index + " not found");
        }

        return true;
    }

    @Override
    public boolean deleteStudent(int index) {
        boolean result = this.context.getStudents().deleteStudent(index);

        if(!result) {
            throw new DataNotFoundException("Student with index " + index + " not found");
        }

        return true;
    }

    @Override
    public Student addStudent(Student student) {
        return this.context.getStudents().addStudent(student);
    }

}
