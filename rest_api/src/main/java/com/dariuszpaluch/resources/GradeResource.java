//package com.dariuszpaluch.resources;
//
//import com.dariuszpaluch.models.Course;
//import com.dariuszpaluch.models.Grade;
//import com.dariuszpaluch.service.CourseService;
//import com.dariuszpaluch.service.GradeService;
//
//import javax.validation.constraints.NotNull;
//import javax.ws.rs.*;
//import javax.ws.rs.core.Context;
//import javax.ws.rs.core.MediaType;
//import javax.ws.rs.core.Response;
//import javax.ws.rs.core.UriInfo;
//import java.net.URI;
//
//@Path("grades")
//@Consumes(value = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
//@Produces(value = {MediaType.TEXT_PLAIN, MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
//public class GradeResource {
//    private final GradeService gradeService;
//
//    public GradeResource() {
//        this.gradeService = new GradeService();
//    }
//
//    @GET
//    public Response getAll() {
//        return Response.ok(gradeService.getAllGrades()).build();
//    }
//
//    @GET
//    @Path("/{index}")
//    public Response get(@PathParam("index") final int id) {
//        Grade result = gradeService.getGrade(id);
//
//        return Response.ok(result).build();
//    }
//
//    @POST
//    public Response post(@NotNull Grade grade, @Context UriInfo uriInfo) {
//        Grade newGrade = gradeService.addGrade(grade);
//
//        int newId = newGrade.getId();
//        URI uri = uriInfo.getAbsolutePathBuilder().path(Integer.toString(newId)).build();
//
//        return Response.created(uri).entity(newGrade).build();
//    }
//
//    @PUT
//    @Path("/{index}")
//    public Response put(@NotNull Grade grade, @PathParam("index") final int id) {
//        gradeService.updateGrade(grade, id);
//
//        return Response.ok().build();
//    }
//
//    @DELETE
//    @Path("/{index}")
//    public Response delete(@PathParam("index") final int id) {
//        gradeService.deleteGrade(id);
//
//        return Response.ok().build();
//    }
//}
