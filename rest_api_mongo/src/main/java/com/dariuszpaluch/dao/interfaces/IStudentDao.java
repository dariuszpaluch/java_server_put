package com.dariuszpaluch.dao.interfaces;

import com.dariuszpaluch.models.Student;

import java.util.List;

public interface IStudentDao {
    public List<Student> getAllStudents();
    public Student getStudent(int index);
    public boolean updateStudent(Student student, int index);
    public boolean deleteStudent(int index);
    public Student addStudent(Student student);
}