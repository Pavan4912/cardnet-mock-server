package com.cardnet.mock.resource;

import com.cardnet.mock.dto.Response.ErrorDto;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

import java.util.List;

@Provider
public class ValidationExceptionMapper implements ExceptionMapper<IllegalArgumentException> {

    @Override
    public Response toResponse(IllegalArgumentException ex) {
        
        List<ErrorDto> errors = List.of(new ErrorDto("400", ex.getMessage()));

        return Response
                .status(400)
                .entity(errors)
                .build();
    }
}
