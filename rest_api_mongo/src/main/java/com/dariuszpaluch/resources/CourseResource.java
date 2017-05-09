package com.dariuszpaluch.resources;

import com.dariuszpaluch.dao.interfaces.ICourseDao;
import com.dariuszpaluch.models.Course;
import com.dariuszpaluch.models.Student;
import com.dariuszpaluch.service.CourseService;
import com.dariuszpaluch.service.GradeService;

import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;

/**
 * Course resource (exposed at "courses" path)
 */
@Path("courses")
@Consumes(value = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
@Produces(value = {MediaType.TEXT_PLAIN, MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
public class CourseResource {
    private final CourseService courseService;
    private final GradeService gradeService;

    public CourseResource() {
        this.courseService = new CourseService();
        this.gradeService = new GradeService();
    }

    @GET
    public Response getAll() {
        return Response.ok(courseService.getAllCourse()).build();
    }

//    @GET
//    @Path("/{index}")
//    public Response get(@PathParam("index") final int id) {
//        Course result = courseService.getCourse(id);
//
//        return Response.ok(result).build();
//    }
//
//    @GET
//    @Path("/{index}/grades")
//    public Response getGrades(@PathParam("index") final int id) {
//        return Response.ok(gradeService.getCourseGrade(id)).build();
//    }
//
//
//    @POST
//    public Response post(@NotNull Course course, @Context UriInfo uriInfo) {
//        Course newCourse = courseService.addCourse(course);
//
//        int newId = newCourse.getId();
//        URI uri = uriInfo.getAbsolutePathBuilder().path(Integer.toString(newId)).build();
//
//        return Response.created(uri).entity(newCourse).build();
//    }
//
//    @PUT
//    @Path("/{index}")
//    public Response put(@NotNull Course course, @PathParam("index") final int id) {
//        courseService.updateCourse(course, id);
//
//        return Response.ok().build();
//    }
//
//    @DELETE
//    @Path("/{index}")
//    public Response delete(@PathParam("index") final int id) {
//        courseService.deleteCourse(id);
//
//        return Response.ok().build();
//    }


}
