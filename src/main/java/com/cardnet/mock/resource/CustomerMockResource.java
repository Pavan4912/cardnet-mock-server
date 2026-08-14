package com.cardnet.mock.resource;

import com.cardnet.mock.dto.Request.*;
import com.cardnet.mock.dto.Response.ApiResponse;
import com.cardnet.mock.dto.Response.CardNetCustomerResponse;
import com.cardnet.mock.dto.Response.PaymentProfile;
import com.cardnet.mock.service.CustomerMockService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.Map;

@Path("/servicios/tokens/v1/api/Customer")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CustomerMockResource {

    @Inject
    CustomerMockService customerService;

    @POST
    @Path("")
    public Response createCustomer(CardNetCustomerRequest request) {
        CardNetCustomerResponse response = customerService.createCustomer(request);
        return Response.ok(new ApiResponse<>(response)).build();
    }

    @GET
    @Path("/{customerId}")
    public Response getCustomer(@PathParam("customerId") Long customerId) {
        CardNetCustomerResponse customer = customerService.getCustomer(customerId);
        if (customer == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(Map.of("Code", "TK011", "Message", "Customer not found"))
                    .build();
        }
        return Response.ok(new ApiResponse<>(customer)).build();
    }

    @POST
    @Path("/{customerId}/update")
    public Response updateCustomer(
            @PathParam("customerId") Long customerId,
            CustomerUpdateRequest request) {

        CardNetCustomerResponse customer = customerService.updateCustomer(customerId, request);

        if (customer == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(Map.of("Code", "TK011", "Message", "Customer not found"))
                    .build();
        }

        return Response.ok(new ApiResponse<>(customer)).build();
    }


    @POST
    @Path("/{customerId}/activate")
    public Response activateCustomerProfile(
            @PathParam("customerId") Long customerId,
            CustomerActivationRequest request) {
            
        // We should probably check if customer exists first, but letting service handle it.
        CardNetCustomerResponse profile = customerService.activatePaymentProfile(request);

        if (profile == null) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(Map.of("Code", "TK009", "Message", "Invalid token activation code."))
                    .build();
        }

        return Response.ok(new ApiResponse<>(profile)).build();
    }

    @POST
    @Path("/{customerId}/PaymentProfileUpdate")
    public Response updatePaymentProfile(
            @PathParam("customerId") Long customerId,
            PaymentProfileUpdateRequest request) {
            
        CardNetCustomerResponse customer = customerService.updatePaymentProfile(customerId, request);

        if (customer == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(Map.of("Code", "TK011", "Message", "Customer not found"))
                    .build();
        }

        return Response.ok(new ApiResponse<>(customer)).build();
    }

    @POST
    @Path("/{customerId}/PaymentProfileDelete")
    public Response deletePaymentProfile(
            @PathParam("customerId") Long customerId,
            PaymentProfileDeleteRequest request) {

        CardNetCustomerResponse customer = customerService.deletePaymentProfile(customerId, request.getPaymentProfileId());

        if (customer == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(Map.of("Code", "TK011", "Message", "Customer not found"))
                    .build();
        }

        return Response.ok(new ApiResponse<>(customer)).build();
    }
}