package com.dariuszpaluch.service;

import com.dariuszpaluch.dao.Context;
import com.dariuszpaluch.dao.GradeDaoImpl;
import com.dariuszpaluch.dao.interfaces.IGradeDao;
import com.dariuszpaluch.exception.DataNotFoundException;
import com.dariuszpaluch.exception.WrongGradeException;
import com.dariuszpaluch.models.Course;
import com.dariuszpaluch.models.Grade;
import com.dariuszpaluch.models.Student;
import com.dariuszpaluch.utils.GradeValid;

import java.util.List;

public class GradeService implements IGradeDao {
  private final IGradeDao gradeDao = new GradeDaoImpl();

  @Override
  public List<Grade> getAllGrades() {
    return this.gradeDao.getAllGrades();
  }

  @Override
  public List<Grade> getStudentGrade(Student student) {
    return this.gradeDao.getStudentGrade(student);
  }

  @Override
  public List<Grade> getCourseGrade(Course course) {
    return this.gradeDao.getCourseGrade(course);
  }

  @Override
  public Grade getGrade(String id) {
    Grade grade = this.gradeDao.getGrade(id);

    if (grade == null) {
      throw new DataNotFoundException("Grade with id " + id + " not found");
    }

    return grade;
  }


  private boolean validGrade(Grade grade) {
    boolean result = GradeValid.gradeValue(grade.getValue());
    if (!result) {
      throw new WrongGradeException();
    }

    return result;
  }

  @Override
  public boolean updateGrade(Grade grade, String id) {
    if (this.validGrade(grade)) {

      boolean result = this.gradeDao.updateGrade(grade, id);
      if (!result) {
        throw new DataNotFoundException("Student with i " + id + " not found");
      }
      return true;

    }

    return false;
  }

  @Override
  public boolean deleteGrade(String id) {
    boolean result = this.gradeDao.deleteGrade(id);

    if (!result) {
      throw new DataNotFoundException("Grade with id " + id + " not found");
    }

    return true;

  }

  @Override
  public Grade addGrade(Grade grade) {
    if (this.validGrade(grade)) {
      return this.gradeDao.addGrade(grade);
    }

    return null;
  }
}
