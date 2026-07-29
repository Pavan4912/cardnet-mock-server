package com.cardnet.mock.resource;

import com.cardnet.mock.config.CardNetMockConfig;
import com.cardnet.mock.dto.TokenizeRequest;
import com.cardnet.mock.dto.TokenizeResponse;
import com.cardnet.mock.service.CardNetMockService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.Map;

@Path("/servicios/tokens/Secure/api")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CardNetMockResource {
    @Inject
    CardNetMockService service;

    @Inject
    CardNetMockConfig config;

    @POST
    @Path("/Token")
    public Response tokenize(
            @QueryParam("commerceKey") String commerceKey,
            TokenizeRequest request
    ) throws InterruptedException {

        if (!config.privateAccountKey().equals(commerceKey)) {

            return Response
                    .status(Response.Status.UNAUTHORIZED)
                    .entity(Map.of(
                            "Error",
                            "Invalid commerce key"
                    ))
                    .build();
        }

//        Thread.sleep(30000);

        return Response.ok(
                service.tokenize(request)
        ).build();
    }

}
