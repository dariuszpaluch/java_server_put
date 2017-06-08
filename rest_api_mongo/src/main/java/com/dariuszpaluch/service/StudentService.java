package com.dariuszpaluch.service;

import com.dariuszpaluch.dao.Context;
import com.dariuszpaluch.dao.CounterDao;
import com.dariuszpaluch.dao.StudentDaoImpl;
import com.dariuszpaluch.dao.interfaces.IStudentDao;
import com.dariuszpaluch.exception.DataNotFoundException;
import com.dariuszpaluch.exception.WrongCompareTypeException;
import com.dariuszpaluch.exception.WrongCreatedDateException;
import com.dariuszpaluch.exception.WrongGradeException;
import com.dariuszpaluch.models.Grade;
import com.dariuszpaluch.models.Student;
import com.dariuszpaluch.utils.BeforeDateValid;
import com.dariuszpaluch.utils.GradeValid;
import com.dariuszpaluch.utils.ValidCompareType;

import java.util.Date;
import java.util.List;

public class StudentService implements IStudentDao {
  private final IStudentDao studentDao = new StudentDaoImpl();
  private final CounterDao counterDao = new CounterDao();

  @Override
  public List<Student> getAllStudents(int index, String firstName, String lastName, Date dateOfBirth, int dateOfBirthCompareType) {
    if (dateOfBirth != null) {
      if (!ValidCompareType.valid(dateOfBirthCompareType)) {
        throw new WrongCompareTypeException();
      }

    }
    return this.studentDao.getAllStudents(index, firstName, lastName, dateOfBirth, dateOfBirthCompareType);
  }

  @Override
  public Student getStudent(int index) {

    Student student = this.studentDao.getStudent(index);

    if (student == null) {
      throw new DataNotFoundException("Student with index " + index + " not found");
    }

    return student;
  }

  private boolean validStudent(Student student) {
    boolean created = BeforeDateValid.valid(student.getDateOfBirth());
    if(!created) {
      throw new WrongCreatedDateException();
    }

    return true;
  }

  @Override
  public boolean updateStudent(Student student, int index) {
    if(validStudent(student)) {
      boolean result = this.studentDao.updateStudent(student, index);

      if (!result) {
        throw new DataNotFoundException("Student with index " + index + " not found");
      }

      return true;
    }

    return false;
  }

  @Override
  public boolean deleteStudent(int index) {
    boolean result = this.studentDao.deleteStudent(index);

    if (!result) {
      throw new DataNotFoundException("Student with index " + index + " not found");
    }

    return true;
  }

  @Override
  public Student addStudent(Student student) {
    if(validStudent(student)) {
      student.setIndex(counterDao.getSeq("students"));

      return this.studentDao.addStudent(student);
    }

    return null;
  }

}
