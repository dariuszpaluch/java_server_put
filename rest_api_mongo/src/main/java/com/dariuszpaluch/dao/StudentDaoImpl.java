package com.dariuszpaluch.dao;

import com.dariuszpaluch.dao.interfaces.IStudentDao;
import com.dariuszpaluch.models.Student;
import com.dariuszpaluch.utils.DatastoreHandlerUtil;
import org.mongodb.morphia.Datastore;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class StudentDaoImpl {

//    private List<Student> students;
//
//    public StudentDaoImpl() {
//        students = new ArrayList<Student>();
//    }
//
//    public StudentDaoImpl(List<Student> students) {
//        this.students = students;
//    }
//
//    public List<Student> getAllStudents() {
//        Datastore datastore = DatastoreHandlerUtil.getInstance().getDatastore();
//
//        return  datastore.find(Student.class).asList();
//    }
//
//    @Override
//    public Student getStudent(int index) {
//        return null;
//    }
//
//    @Override
//    public boolean updateStudent(Student student, int index) {
//        return false;
//    }
//
//    @Override
//    public boolean deleteStudent(int index) {
//        return false;
//    }
//
//    @Override
//    public Student addStudent(Student student) {
//        return null;
//    }
//
//    @Override
//    public Student getStudent(int index) {
//        Datastore datastore = DatastoreHandlerUtil.getInstance().getDatastore();
//        List<Student> students = datastore.find(Student.class).asList();
//
//        for(Student item : students) {
//            if(item.getIndex() == index) {
//                return item;
//            }
//        }
//
//        return null;
//    }

//    @Override
//    public boolean updateStudent(Student student, int index) {
//        for(Student item: students) {
//            if(item.getIndex() == index) {
//                item.setName(student.getName());
//                item.setDateOfBirth(student.getDateOfBirth());
//                item.setSurname(student.getSurname());
//                return true;
//            }
//        }
//
//        return false;
//    }
//
//    @Override
//    public boolean deleteStudent(int index) {
//        for(Student item : students) {
//            if(item.getIndex() == index) {
//                students.remove(item);
//                return true;
//            }
//        }
//
//        return  false;
//    }
//
//    @Override
//    public Student addStudent(Student student) {
//        students.add(student);
//
//        return this.getStudent(student.getIndex());
//    }
}
