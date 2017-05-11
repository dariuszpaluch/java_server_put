package com.dariuszpaluch.exception;

import com.dariuszpaluch.utils.ErrorMessage;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class WrongCompareTypeMapper implements ExceptionMapper<WrongCompareTypeException> {
  @Override
  public Response toResponse(WrongCompareTypeException e) {
    ErrorMessage errorMessage = new ErrorMessage(e.getMessage(), Response.Status.BAD_REQUEST.getStatusCode());

    return Response.status(Response.Status.BAD_REQUEST).entity(errorMessage).build();
  }
}
