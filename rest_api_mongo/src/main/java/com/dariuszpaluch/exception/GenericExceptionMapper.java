package com.dariuszpaluch.exception;

import com.dariuszpaluch.utils.ErrorMessage;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class GenericExceptionMapper implements ExceptionMapper<Throwable> {
    @Override
    public Response toResponse(Throwable throwable) {
        ErrorMessage errorMessage = new ErrorMessage(throwable.getMessage(), Response.Status.INTERNAL_SERVER_ERROR.getStatusCode());

        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(errorMessage).build();
    }
}
