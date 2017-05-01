package com.dariuszpaluch.resources;

import com.dariuszpaluch.dao.interfaces.ICourseDao;
import com.dariuszpaluch.service.CourseService;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Course resource (exposed at "courses" path)
 */
@Path("courses")
@Consumes(value = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
@Produces(value = {MediaType.TEXT_PLAIN, MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
public class CourseResource {
    private final CourseService courseService;

    public CourseResource() {
        this.courseService = new CourseService();
    }

    @GET
    public Response getAll() {
        return Response.ok(courseService.getAllCourse()).build();
    }

}
