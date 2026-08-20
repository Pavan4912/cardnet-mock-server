package com.cardnet.mock.service;

import com.cardnet.mock.dto.Request.CardNetPurchaseRequest;
import com.cardnet.mock.dto.Response.CardNetPurchaseResponse;
import com.cardnet.mock.dto.Response.Transaction;
import com.cardnet.mock.dto.Response.TransactionStep;
import jakarta.enterprise.context.ApplicationScoped;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.time.Instant;
import java.util.stream.Collectors;

@ApplicationScoped
public class PurchaseMockService {

    private final Map<String, CardNetPurchaseResponse> purchases = new ConcurrentHashMap<>();
    private final AtomicLong sequence = new AtomicLong(100000);
    private final AtomicLong transactionSequence = new AtomicLong(100);

    public CardNetPurchaseResponse createPurchase(CardNetPurchaseRequest request) {
        String purchaseId = String.valueOf(sequence.incrementAndGet());
        CardNetPurchaseResponse response = new CardNetPurchaseResponse();

        response.setPurchaseId(purchaseId);
        response.Created = Instant.now().toString();
        response.TrxToken = request.TrxToken;
        response.Order = request.Order;
        response.Amount = request.Amount;
        response.Currency = request.Currency;
        response.Capture = request.Capture;
        response.Tip = request.Tip;
        response.Installments = request.Installments;
        response.Description = request.Description;
        response.UniqueID = request.UniqueID;
        response.AdditionalData = request.AdditionalData;
        response.CustomerUserAgent = request.CustomerUserAgent;
        response.CustomerIP = request.CustomerIP;
        response.DataDo = request.DataDo;

        Transaction t = new Transaction();
        t.TransactionID = transactionSequence.incrementAndGet();
        t.Created = response.Created;

        if (request.Capture != null && !request.Capture) {
            t.TransactionStatusId = 3;
            t.Status = "Preauthorized";
            t.Description = "Preauthorized";
            t.ApprovalCode = "00";
        } else {
            t.TransactionStatusId = 1;
            t.Status = "Approved";
            t.Description = "Approved";
            t.ApprovalCode = "00";
        }

        TransactionStep step = new TransactionStep();
        step.Step = "Purchase";
        step.Created = response.Created;
        step.Status = t.TransactionStatusId;
        step.ResponseCode = "00";
        step.ResponseMessage = "Approved";
        step.AuthorizationCode = t.ApprovalCode;
        step.UniqueId = request.UniqueID;
        t.Steps = new ArrayList<>();
        t.Steps.add(step);

        response.Transaction = t;

        purchases.put(purchaseId, response);
        return response;
    }

    public CardNetPurchaseResponse getPurchase(String purchaseId) {
        return purchases.get(purchaseId);
    }

    public CardNetPurchaseResponse refundPurchase(String purchaseId) {
        CardNetPurchaseResponse purchase = purchases.get(purchaseId);
        if (purchase != null && purchase.Transaction != null) {
            purchase.Transaction.Status = "REFUNDED";
            purchase.Transaction.Description = "Refunded";

            TransactionStep step = new TransactionStep();
            step.Step = "Refund";
            step.Created = Instant.now().toString();
            step.Status = 4; // Or some refund status
            step.ResponseCode = "00";
            step.ResponseMessage = "Refunded";
            purchase.Transaction.Steps.add(step);
        }
        return purchase;
    }

    public CardNetPurchaseResponse commitPurchase(String purchaseId) {
        CardNetPurchaseResponse purchase = purchases.get(purchaseId);
        if (purchase != null && purchase.Transaction != null) {
            if ("Preauthorized".equals(purchase.Transaction.Status)) {
                purchase.Transaction.Status = "Approved";
                purchase.Transaction.Description = "Approved";
                purchase.Transaction.TransactionStatusId = 1;

                TransactionStep step = new TransactionStep();
                step.Step = "Commit";
                step.Created = Instant.now().toString();
                step.Status = 1;
                step.ResponseCode = "00";
                step.ResponseMessage = "Committed";
                purchase.Transaction.Steps.add(step);
            } else {
                throw new IllegalArgumentException("Purchase is not preauthorized");
            }
        }
        return purchase;
    }

    public List<CardNetPurchaseResponse> listPurchases(
            Long customerId,
            String from,
            String to,
            Long paymentMediaId,
            Boolean authorized,
            String orderNumber) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");

        // Documentation:
        // From -> defaults to current date
        // To   -> defaults to current date
        LocalDate fromDate = from != null
                ? LocalDate.parse(from, formatter)
                : LocalDate.now();

        LocalDate toDate = to != null
                ? LocalDate.parse(to, formatter)
                : LocalDate.now();

        // Documentation:
        // Authorized -> defaults to false
        boolean authorizedFilter = Boolean.TRUE.equals(authorized);

        return purchases.values().stream()
                .filter(p -> {

                    // CustomerID filter
                    //
                    // CardNetPurchaseResponse contains Customer,
                    // but the exact CustomerID field depends on
                    // CardNetCustomerResponse.
                    if (customerId != null) {
                        if (p.Customer == null) {
                            return false;
                        }

                        // Add the actual CustomerID comparison here
                        // once the CardNetCustomerResponse field is known.
                    }

                    // From / To date filter
                    if (p.Created != null) {
                        LocalDate purchaseDate;

                        try {
                            // If Created is already yyyyMMdd
                            purchaseDate = LocalDate.parse(
                                    p.Created,
                                    formatter
                            );
                        } catch (Exception e) {
                            // If Created contains a timestamp/date-time,
                            // this needs to be parsed according to the
                            // actual format stored in Created.
                            return false;
                        }

                        if (purchaseDate.isBefore(fromDate)
                                || purchaseDate.isAfter(toDate)) {
                            return false;
                        }
                    }

                    // PaymentMediaId filter
                    //
                    // CardNetPurchaseResponse currently does not have
                    // a PaymentMediaId field, so this cannot be applied
                    // directly yet.

                    // Authorized filter
                    if (authorizedFilter) {
                        if (p.Transaction == null
                                || !"Approved".equals(p.Transaction.Status)) {
                            return false;
                        }
                    }

                    // OrderNumber filter
                    if (orderNumber != null
                            && !orderNumber.equals(p.Order)) {
                        return false;
                    }

                    return true;
                })
                .collect(Collectors.toList());
    }
}