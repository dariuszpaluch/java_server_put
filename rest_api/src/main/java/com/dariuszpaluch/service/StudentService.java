package com.dariuszpaluch.service;

import com.dariuszpaluch.dao.Context;
import com.dariuszpaluch.dao.interfaces.IStudentDao;
import com.dariuszpaluch.exception.DataNotFoundException;
import com.dariuszpaluch.exception.WrongDateFormatException;
import com.dariuszpaluch.models.Grade;
import com.dariuszpaluch.models.Student;
import com.dariuszpaluch.utils.DateValid;

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

    private boolean validDate(Student student) {
        boolean result = DateValid.dateValid(student.getDateOfBirth());

        if(!result) {
            throw new WrongDateFormatException();
        }

        return true;
    }

    @Override
    public boolean updateStudent(Student student, int index) {

        if(this.validDate(student)) {
            boolean result = this.context.getStudents().updateStudent(student, index);

            if(!result) {
                throw new DataNotFoundException("Student with index " + index + " not found");
            }

            return true;
        }

        return false;
    }

    @Override
    public boolean deleteStudent(int index) {
        boolean result = this.context.getStudents().deleteStudent(index);

        if(!result) {
            throw new DataNotFoundException("Student with index " + index + " not found");
        }

        this.context.getGrades().deleteGradesByStudent(index);

        return true;
    }

    @Override
    public Student addStudent(Student student) {
        if(this.validDate(student)) {
            return this.context.getStudents().addStudent(student);
        }

        return null;
    }

}
