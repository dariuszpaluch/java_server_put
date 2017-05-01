package com.dariuszpaluch.resources;


import com.dariuszpaluch.dao.StudentDaoImpl;
import com.dariuszpaluch.dao.interfaces.IStudentDao;
import com.dariuszpaluch.models.Course;
import com.dariuszpaluch.models.Grade;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Calendar;

/**
 * Student resource (exposed at "students" path)
 */
@Path("students")
public class StudentResource {

    IStudentDao studens = new StudentDaoImpl();

    @GET
    @Produces({MediaType.TEXT_PLAIN, MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getAll() {
        return Response.ok(studens.getAllStudents()).build();
    }


    @GET
    @Path("test/{index}")
    @Produces({MediaType.TEXT_PLAIN, MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Grade get2() {
        return new Grade(5.0, Calendar.getInstance(), new Course());
    }
}
