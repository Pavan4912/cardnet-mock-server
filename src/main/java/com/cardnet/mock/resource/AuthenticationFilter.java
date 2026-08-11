package com.cardnet.mock.resource;

//import com.cardnet.mock.config.CardNetMockConfig;
import jakarta.inject.Inject;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.Provider;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Provider
public class AuthenticationFilter implements ContainerRequestFilter {

    @org.eclipse.microprofile.config.inject.ConfigProperty(name = "cardnet.mock.private-account-key")
    String privateAccountKey;

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        String path = requestContext.getUriInfo().getPath();

        // Direct Tokenization API uses query param, not Basic Auth
        if (path.contains("/Secure/api/Token")) {
            return;
        }

        // Only protect /v1/api/ paths
        if (path.contains("/v1/api/")) {
            String authorizationHeader = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);

            if (authorizationHeader == null || !authorizationHeader.startsWith("Basic ")) {
                abortWithUnauthorized(requestContext);
                return;
            }

            String base64Credentials = authorizationHeader.substring("Basic ".length()).trim();
            byte[] credDecoded = Base64.getDecoder().decode(base64Credentials);
            String credentials = new String(credDecoded, StandardCharsets.UTF_8);
            
            // credentials format is "username:password"
            final String[] values = credentials.split(":", 2);
            String username = values[0];
            
            // The username must be the private account key (or public for some, but we'll accept private for now)
            if (!username.equals(privateAccountKey)) {
                abortWithUnauthorized(requestContext);
            }
        }
    }

    private void abortWithUnauthorized(ContainerRequestContext requestContext) {
        requestContext.abortWith(
                Response.status(Response.Status.UNAUTHORIZED)
                        .entity("{\"Code\": \"401\", \"Message\": \"Unauthorized\"}")
                        .build()
        );
    }
}
