package com.dariuszpaluch.resources;


import com.dariuszpaluch.dao.StudentDaoImpl;
import com.dariuszpaluch.dao.interfaces.IStudentDao;
import com.dariuszpaluch.models.Grade;
import com.dariuszpaluch.models.Student;
import com.dariuszpaluch.service.GradeService;
import com.dariuszpaluch.service.StudentService;

import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;

/**
 * Student resource (exposed at "students" path)
 */
@Path("students")
@Consumes(value = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
@Produces(value = {MediaType.TEXT_PLAIN, MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
public class StudentResource {
    private final StudentService studentService;
    private final GradeService gradeService;

    public StudentResource() {
        this.studentService = new StudentService();
        this.gradeService = new GradeService();
    }

    @GET
    public Response getAll() {
        return Response.ok(studentService.getAllStudents()).build();
    }


    @GET
    @Path("/{index}")
    public Response get(@PathParam("index") final String index) {
        Student result = studentService.getStudent(index);

        return Response.ok(result).build();
    }

    @GET
    @Path("/{index}/grades")
    public Response getGrades(@PathParam("index") final String index) {
        return Response.ok(gradeService.getStudentGrade(index)).build();
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
        studentService.updateStudent(student, index);

        return Response.ok().build();
    }

    @DELETE
    @Path("/{index}")
    public Response delete(@PathParam("index") final String index) {
        boolean result = studentService.deleteStudent(index);

        return Response.ok().build();
    }


    @POST
    @Path("/{index}/grades")
    public Response postGrade(@NotNull Grade grade, @PathParam("index") final String index,  @Context UriInfo uriInfo) {
        grade.setStudentIndex(index);
        Grade newGrade = gradeService.addGrade(grade);

        int newId = newGrade.getId();
        URI uri = uriInfo.getAbsolutePathBuilder().path(Integer.toString(newId)).build();

        return Response.created(uri).entity(newGrade).build();
    }

    @PUT
    @Path("/{index}/grades/{id}")
    public Response put(@NotNull Grade grade,  @PathParam("index") final String index, @PathParam("id") final int id) {
        grade.setStudentIndex(index);
        gradeService.updateGrade(grade, id);

        return Response.ok().build();
    }

    @DELETE
    @Path("/{index}/grades/{id}")
    public Response delete(@PathParam("index") final int id, @PathParam("index") final String index) {
        gradeService.deleteGrade(id);

        return Response.ok().build();
    }


}
