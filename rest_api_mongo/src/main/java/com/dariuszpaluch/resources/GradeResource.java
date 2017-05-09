//package com.dariuszpaluch.resources;
//
//import com.dariuszpaluch.models.Grade;
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
//@Path("students/{index}")
//@Consumes(value = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
//@Produces(value = {MediaType.TEXT_PLAIN, MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
//public class GradeResource {
//    private final GradeService gradeService;
//
//    public GradeResource() {
//        this.gradeService = new GradeService();
//    }
//
//  @GET
//  @Path("/grades")
//  public Response getGrades(@PathParam("index") final int index) {
//    return Response.ok(gradeService.getStudentGrade(index)).build();
//  }
//
//  @POST
//  @Path("/grades")
//  public Response postGrade(@NotNull Grade grade, @PathParam("index") final int index, @Context UriInfo uriInfo) {
//    grade.setStudentIndex(index);
//    Grade newGrade = gradeService.addGrade(grade);
//
//    int newId = newGrade.getId();
//    URI uri = uriInfo.getAbsolutePathBuilder().path(Integer.toString(newId)).build();
//
//    return Response.created(uri).entity(newGrade).build();
//  }
//
//  @PUT
//  @Path("/grades/{id}")
//  public Response put(@NotNull Grade grade, @PathParam("index") final int index, @PathParam("id") final int id) {
//    grade.setStudentIndex(index);
//    gradeService.updateGrade(grade, id);
//
//    return Response.ok().build();
//  }
//
//  @DELETE
//  @Path("/grades/{id}")
//  public Response delete(@PathParam("index") final int id, @PathParam("index") final int index) {
//    gradeService.deleteGrade(id);
//
//    return Response.ok().build();
//  }
//
//}
