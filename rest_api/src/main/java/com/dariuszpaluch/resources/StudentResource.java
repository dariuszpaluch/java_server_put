package com.dariuszpaluch.resources;


import com.dariuszpaluch.dao.StudentDaoImpl;
import com.dariuszpaluch.dao.interfaces.IStudentDao;
import com.dariuszpaluch.models.Course;
import com.dariuszpaluch.models.Grade;
import com.dariuszpaluch.models.Student;
import com.dariuszpaluch.service.StudentService;
import org.glassfish.jersey.linking.InjectLink;
import org.glassfish.jersey.linking.InjectLinks;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import javax.ws.rs.ext.Provider;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Calendar;

/**
 * Student resource (exposed at "students" path)
 */
@Path("students")
@Consumes(value = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
@Produces(value = {MediaType.TEXT_PLAIN, MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
public class StudentResource {

    private final IStudentDao students = new StudentDaoImpl();

    private final StudentService studentService;

    public StudentResource() {
        this.studentService = new StudentService();
    }

    @GET
    public Response getAll() {
        return Response.ok(students.getAllStudents()).build();
    }


    @GET
    @Path("/{index}")
    public Response get(@PathParam("index") final String index) {
        Student result = studentService.getStudent(index);

        return Response.ok(result).build();
    }

    @POST
    public Response post(@NotNull Student student, @Context UriInfo uriInfo) {
        Student newStudent = studentService.addStudent(student);

        String newIndex = newStudent.getIndex();
        URI uri = uriInfo.getAbsolutePathBuilder().path(newIndex).build();

        return Response.created(uri).entity(newStudent).build();
    }

    @PUT
    @Path("/{index}")
    public Response put(@NotNull Student student, @PathParam("index") final String index) {
        student.setIndex(index);
        studentService.updateStudent(student, student.getIndex());

        return Response.ok().build();
    }

    @DELETE
    @Path("/{index}")
    public Response delete(@PathParam("index") final String index) {
        boolean result = students.deleteStudent(index);

        return Response.ok().build();
    }


}
