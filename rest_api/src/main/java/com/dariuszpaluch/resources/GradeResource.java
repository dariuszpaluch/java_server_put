package com.dariuszpaluch.resources;

import com.dariuszpaluch.models.Course;
import com.dariuszpaluch.models.Grade;
import com.dariuszpaluch.models.Student;
import com.dariuszpaluch.service.CourseService;
import com.dariuszpaluch.service.GradeService;
import com.dariuszpaluch.service.StudentService;
import com.dariuszpaluch.view_models.GradeView;

import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.ArrayList;
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
    public List<GradeView> getGrades(@PathParam("index") final int index) {
        Student student = studentService.getStudent(index);
        List<GradeView> result = new ArrayList<>();
        List<Grade> grades = gradeService.getStudentGrade(index);

        for (Grade item : grades) {
            Course course = courseService.getCourse(item.getCourseId());
            result.add(new GradeView(item, course.getName(), student.getName()));
        }

        return result;
    }

    @POST
    public Response postGrade(@NotNull Grade grade, @PathParam("index") final int index, @Context UriInfo uriInfo) {
        Student student = studentService.getStudent(index);
        Course course = courseService.getCourse(grade.getCourseId());

        grade.setStudentIndex(index);
        Grade newGrade = gradeService.addGrade(grade);

        int newId = newGrade.getId();
        URI uri = uriInfo.getAbsolutePathBuilder().path(Integer.toString(newId)).build();

        return Response.created(uri).entity(new GradeView(newGrade, course.getName(), course.getName())).build();
    }

    @PUT
    @Path("/{id}")
    public Response put(@NotNull Grade grade, @PathParam("index") final int index, @PathParam("id") final int id) {
        Student student = studentService.getStudent(index);
        grade.setStudentIndex(index);
        gradeService.updateGrade(grade, id);

        return Response.ok().build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("index") final int id, @PathParam("index") final int index) {
        Student student = studentService.getStudent(index);
        gradeService.deleteGrade(id);

        return Response.ok().build();
    }

    @GET
    @Path("/{id}")
    public Response get(@PathParam("id") final int id, @PathParam("index") final int index) {
        Grade grade = gradeService.getGrade(id);
        Student student = studentService.getStudent(grade.getStudentIndex());
        Course course = courseService.getCourse(grade.getCourseId());

        return Response.ok().entity(new GradeView(grade, course.getName(), student.getName())).build();
    }
}
