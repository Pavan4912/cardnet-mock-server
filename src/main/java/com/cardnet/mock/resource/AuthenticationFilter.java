package com.cardnet.mock.resource;

import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.Provider;

import java.io.IOException;

@Provider
public class AuthenticationFilter implements ContainerRequestFilter {

    @org.eclipse.microprofile.config.inject.ConfigProperty(
            name = "cardnet.mock.private-account-key"
    )
    String privateAccountKey;

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {

        String path = requestContext.getUriInfo().getPath();

        // Token API does not require Basic Auth
        if (path.contains("/Secure/api/Token")) {
            return;
        }

        // Require auth for /v1/api/*
        if (path.contains("/v1/api/")) {

            String authorizationHeader =
                    requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);

            String expectedAuthorization =
                    "Basic " + privateAccountKey;

            if (!expectedAuthorization.equals(authorizationHeader)) {
                abortWithUnauthorized(requestContext);
            }
        }
    }

    private void abortWithUnauthorized(
            ContainerRequestContext requestContext) {

        requestContext.abortWith(
                Response.status(Response.Status.UNAUTHORIZED)
                        .entity("{\"Code\":\"401\",\"Message\":\"Unauthorized\"}")
                        .type("application/json")
                        .build()
        );
    }
}