package com.dariuszpaluch.dao.interfaces;

import com.dariuszpaluch.models.Student;

import java.util.List;

public interface IStudentDao {
    public List<Student> getAllStudents();
    public Student getStudent(int index);
    public void updateStudent(Student student);
    public void deleteStudent(Student student);
}