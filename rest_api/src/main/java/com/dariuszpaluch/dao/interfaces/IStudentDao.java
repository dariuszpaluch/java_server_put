package com.dariuszpaluch.dao.interfaces;

import com.dariuszpaluch.models.Student;

import java.util.List;

public interface IStudentDao {
    public List<Student> getAllStudents();
    public Student getStudent(String index);
    public boolean updateStudent(Student student, String index);
    public boolean deleteStudent(String index);
    public Student addStudent(Student student);
}