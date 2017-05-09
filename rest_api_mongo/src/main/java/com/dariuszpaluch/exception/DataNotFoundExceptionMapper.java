package com.dariuszpaluch.exception;

import com.dariuszpaluch.utils.ErrorMessage;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class DataNotFoundExceptionMapper implements ExceptionMapper<DataNotFoundException> {
    @Override
    public Response toResponse(DataNotFoundException e) {
        ErrorMessage errorMessage = new ErrorMessage(e.getMessage(), Response.Status.NOT_FOUND.getStatusCode());

        return Response.status(Response.Status.NOT_FOUND).entity(errorMessage).build();
    }
}
