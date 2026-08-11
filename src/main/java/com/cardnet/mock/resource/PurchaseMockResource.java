package com.cardnet.mock.resource;

import com.cardnet.mock.dto.Request.CardNetPurchaseRequest;
import com.cardnet.mock.dto.Response.CardNetPurchaseResponse;
import com.cardnet.mock.service.PurchaseMockService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;
import java.util.Map;

@Path("/servicios/tokens/v1/api/purchase")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class PurchaseMockResource {

    @Inject
    PurchaseMockService purchaseService;

    /**
     * Create Purchase
     */
    @POST
    public Response createPurchase(CardNetPurchaseRequest request) {
        CardNetPurchaseResponse response = purchaseService.createPurchase(request);
        return Response.ok(response).build();
    }

    /**
     * Get Purchase By Id
     */
    @GET
    @Path("/{purchaseId}")
    public Response getPurchase(@PathParam("purchaseId") String purchaseId) {
        CardNetPurchaseResponse purchase = purchaseService.getPurchase(purchaseId);

        if (purchase == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(Map.of("error", "Purchase not found"))
                    .build();
        }

        return Response.ok(purchase).build();
    }

    /**
     * Refund Purchase
     */
    @POST
    @Path("/{purchaseId}/refund")
    public Response refundPurchase(@PathParam("purchaseId") String purchaseId) {
        CardNetPurchaseResponse purchase = purchaseService.refundPurchase(purchaseId);

        if (purchase == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(Map.of("error", "Purchase not found"))
                    .build();
        }

        return Response.ok(purchase).build();
    }

    /**
     * Commit Purchase
     */
    @POST
    @Path("/{purchaseId}/commit")
    public Response commitPurchase(@PathParam("purchaseId") String purchaseId) {
        CardNetPurchaseResponse purchase = purchaseService.commitPurchase(purchaseId);

        if (purchase == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(Map.of("error", "Purchase not found"))
                    .build();
        }

        return Response.ok(purchase).build();
    }

    /**
     * List Purchases
     */
    @GET
    public Response listPurch0ases
    (
      @QueryParam("customerId") Long customerId,
      @QueryParam("from") String from,
      @QueryParam("to") String to,
      @QueryParam("paymentMediaId") Long paymentMediaId,
      @QueryParam("authorized") Boolean authorized,
      @QueryParam("orderNumber") String orderNumber)
      {
          List<CardNetPurchaseResponse> purchases = purchaseService.listPurchases
          ( customerId, from,to, paymentMediaId, authorized, orderNumber );
          return Response.ok(purchases).build();
      }
}