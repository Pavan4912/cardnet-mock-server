package com.cardnet.mock.resource;

//import com.cardnet.mock.config.CardNetMockConfig;
import com.cardnet.mock.dto.Request.TokenizeRequest;
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

    @org.eclipse.microprofile.config.inject.ConfigProperty(name = "cardnet.mock.private-account-key")
    String privateAccountKey;

    @POST
    @Path("/Token")
    public Response tokenize(
            @QueryParam("commerceKey") String commerceKey,
            TokenizeRequest request
    ) {

        if (!privateAccountKey.equals(commerceKey)) {
            return Response.status(Response.Status.UNAUTHORIZED)
                    .entity(Map.of("Error", "Invalid commerce key"))
                    .build();
        }

        try {
            return Response.ok(service.tokenize(request)).build();

        } catch (Exception ex) {

            ex.printStackTrace();

            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(Map.of(
                            "error", ex.getClass().getSimpleName(),
                            "message", ex.getMessage()
                    ))
                    .build();
        }
    }

}
