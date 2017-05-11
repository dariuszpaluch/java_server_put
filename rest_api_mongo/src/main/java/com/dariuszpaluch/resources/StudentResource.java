package com.dariuszpaluch.resources;


import com.dariuszpaluch.dao.CounterDao;
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
import java.util.Date;

/**
 * Student resource (exposed at "students" path)
 */
@Path("students")
@Consumes(value = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
@Produces(value = {MediaType.TEXT_PLAIN, MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
public class StudentResource {
  private final StudentService studentService;

  public StudentResource() {
    this.studentService = new StudentService();
  }

  @GET
  public Response getAll(
    @QueryParam("firstName") String firstName,
    @QueryParam("lastName") String lastName,
    @QueryParam("dateOfBirth") Date dateOfBirth,
    @DefaultValue("0")  @QueryParam("dateOfBirthCompareType") int dateOfBirthCompareType //0 '<', 1 '=', 2 '>'
  ) {

    return Response.ok(studentService.getAllStudents(firstName, lastName, dateOfBirth, dateOfBirthCompareType)).build();
  }


  @GET
  @Path("/{index}")
  public Response get(@PathParam("index") final int index) {
    Student result = studentService.getStudent(index);

    return Response.ok(result).build();
  }


  @POST
  public Response post(Student student, @Context UriInfo uriInfo) {
    Student newStudent = studentService.addStudent(student);


    int newIndex = newStudent.getIndex();
    URI uri = uriInfo.getAbsolutePathBuilder().path(Integer.toString(newIndex)).build();

    return Response.created(uri).entity(newStudent).build();
  }

  @PUT
  @Path("/{index}")
  public Response put(@NotNull Student student, @PathParam("index") final int index) {
    studentService.updateStudent(student, index);

    return Response.ok().build();
  }

  @DELETE
  @Path("/{index}")
  public Response delete(@PathParam("index") final int index) {
    boolean result = studentService.deleteStudent(index);

    return Response.ok().build();
  }
}

