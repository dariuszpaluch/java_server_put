package com.dariuszpaluch.resources;

import com.dariuszpaluch.models.Course;
import com.dariuszpaluch.models.Grade;
import com.dariuszpaluch.models.Student;
import com.dariuszpaluch.service.CourseService;
import com.dariuszpaluch.service.GradeService;
import com.dariuszpaluch.service.StudentService;

import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Path("students/{index}/grades")
@Consumes(value = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
@Produces(value = {MediaType.TEXT_PLAIN, MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
public class GradeResource {
  private final GradeService gradeService;
  private final StudentService studentService;
  private final CourseService courseService;

  public GradeResource() {
    this.gradeService = new GradeService();
    this.studentService = new StudentService();
    this.courseService = new CourseService();
  }

  @GET
  public List<Grade> getGrades(
          @PathParam("index") final int index,
          @QueryParam("courseIdQuery") String courseId,
          @QueryParam("courseNameQuery") String courseName,
          @QueryParam("createdQuery") Date created,
          @DefaultValue("0") @QueryParam("compareValueType") int compareType, //-1 '<', 0 '=', 1 '>'
          @DefaultValue("0") @QueryParam("valueQuery") int compareValue
  ) {
    Student student = studentService.getStudent(index);
    Course course = null;
    if(courseId != null) {
      course = courseService.getCourse(courseId);
    }

    List<Course> courses = null;

    return gradeService.getStudentGrade(student, course, compareType, compareValue, courseName, created);
  }

  @POST
  public Response postGrade(@NotNull Grade grade, @PathParam("index") final int index, @Context UriInfo uriInfo) {
    Student student = studentService.getStudent(index);

    Course course = courseService.getCourse(grade.getCourse().getId().toString());
    grade.setCourse(course);
    grade.setStudent(student);
    Grade newGrade = gradeService.addGrade(grade);

    String newId = newGrade.getId().toString();
    URI uri = uriInfo.getAbsolutePathBuilder().path(newId).build();

    return Response.created(uri).entity(newGrade).build();
  }

  @PUT
  @Path("/{id}")
  public Response put(@NotNull Grade grade, @PathParam("index") final int index, @PathParam("id") final String id) {
//        Grade grade = new Grade(gradeView.getValue(), gradeView.getCreated(), gradeView.getCourse().getId(), gradeView.getStudent().getIndex(), gradeView.getId());

    Student student = studentService.getStudent(index);
    Course course = courseService.getCourse(grade.getCourse().getId().toString());
    grade.setStudent(student);
    grade.setCourse(course);
    gradeService.updateGrade(grade, id);

    return Response.ok().build();
  }

  @DELETE
  @Path("/{id}")
  public Response delete(@PathParam("id") final String id, @PathParam("index") final int index) {
    Student student = studentService.getStudent(index);
    gradeService.deleteGrade(id);

    return Response.ok().build();
  }

  @GET
  @Path("/{id}")
  public Response get(@PathParam("id") final String id, @PathParam("index") final int index) {
    Student student = studentService.getStudent(index);
    Grade grade = gradeService.getGrade(id);


    return Response.ok().entity(grade).build();
  }
}
