package com.cardnet.mock.resource;

import com.cardnet.mock.dto.TokenizeResponse;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

import java.time.Instant;

@Provider
public class ValidationExceptionMapper
        implements ExceptionMapper<IllegalArgumentException> {

    @Override
    public Response toResponse(IllegalArgumentException ex) {

        TokenizeResponse response =
                new TokenizeResponse(
                        null,
                        Instant.now().toString(),
                        null,
                        null,
                        null,
                        null,
                        null,
                        null,
                        ex.getMessage()
                );

        return Response
                .status(400)
                .entity(response)
                .build();
    }
}

