package com.dariuszpaluch.exception;

import com.dariuszpaluch.models.ErrorMessage;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

public class WrongDateFormatExceptionMapper  implements ExceptionMapper<WrongGradeException> {
    @Override
    public Response toResponse(WrongGradeException e) {
        ErrorMessage errorMessage = new ErrorMessage(e.getMessage(), Response.Status.BAD_REQUEST.getStatusCode());

        return Response.status(Response.Status.BAD_REQUEST).entity(errorMessage).build();
    }
}
