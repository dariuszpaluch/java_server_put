package com.dariuszpaluch.dao.interfaces;

import com.dariuszpaluch.models.Student;

import java.util.Date;
import java.util.List;

public interface IStudentDao {
    public List<Student> getAllStudents(String name, String surname, Date dateOfBirth, int dateOfBirthCompareType);
    public Student getStudent(int index);
    public boolean updateStudent(Student student, int index);
    public boolean deleteStudent(int index);
    public Student addStudent(Student student);
}