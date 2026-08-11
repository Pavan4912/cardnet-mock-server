package com.cardnet.mock.service;

import com.cardnet.mock.dto.Request.*;
import com.cardnet.mock.dto.Response.CardNetCustomerResponse;
import com.cardnet.mock.dto.Response.PaymentProfile;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.time.Instant;

@ApplicationScoped
public class CustomerMockService {

    private final Map<Long, CardNetCustomerResponse> customers = new ConcurrentHashMap<>();
    private final AtomicLong sequence = new AtomicLong(1000);
    private final AtomicLong paymentProfileSequence = new AtomicLong(1);

    public CardNetCustomerResponse createCustomer(CardNetCustomerRequest request) {
        Long customerId = sequence.incrementAndGet();
        CardNetCustomerResponse response = new CardNetCustomerResponse();

        response.setCustomerId(customerId);
        response.setCommerceCustomerId(request.CommerceCustomerId);
        response.setFirstName(request.FirstName);
        response.setLastName(request.LastName);
        response.setEmail(request.Email);
        response.setPhone(request.PhoneNumber);
        response.setEnabled(request.Enabled != null ? request.Enabled : true);
        response.setDocumentTypeId(request.DocumentTypeId);
        response.setDocNumber(request.DocNumber);
        response.setShippingAddress(request.ShippingAddress);
        response.setBillingAddress(request.BillingAddress);
        response.setAdditionalData(request.AdditionalData);
        response.setPaymentProfiles(new ArrayList<>());
        response.Created = Instant.now().toString();

        customers.put(customerId, response);
        return response;
    }

    public CardNetCustomerResponse getCustomer(Long customerId) {
        return customers.get(customerId);
    }

    public CardNetCustomerResponse activatePaymentProfile(CustomerActivationRequest request) {
        // Mock finding the profile across customers for activation
        for (CardNetCustomerResponse customer : customers.values()) {
            for (PaymentProfile profile : customer.getPaymentProfiles()) {
                if (profile.Token != null && profile.Token.equals(request.Token)) {
                    profile.setActive(true);
                    return customer;
                }
            }
        }
        return null;
    }

    public CardNetCustomerResponse updatePaymentProfile(Long customerId, PaymentProfileUpdateRequest request) {
        CardNetCustomerResponse customer = customers.get(customerId);
        if (customer == null) return null;

        for (PaymentProfile profile : customer.getPaymentProfiles()) {
            if (profile.PaymentProfileId != null && profile.PaymentProfileId.equals(request.PaymentProfileID)) {
                profile.setExpiration(request.Expiration);
                profile.setActive(request.Enable);
                break;
            }
        }
        return customer;
    }

    public CardNetCustomerResponse deletePaymentProfile(Long customerId, Long paymentProfileId) {
        CardNetCustomerResponse customer = customers.get(customerId);
        if (customer == null || customer.getPaymentProfiles() == null) return null;

        customer.getPaymentProfiles().removeIf(profile -> profile.PaymentProfileId != null && profile.PaymentProfileId.equals(paymentProfileId));
        return customer;
    }

    public CardNetCustomerResponse updateCustomer(Long customerId, CustomerUpdateRequest request) {
        CardNetCustomerResponse customer = customers.get(customerId);
        if (customer == null) return null;

        customer.setCommerceCustomerId(request.CommerceCustomerId);
        customer.setFirstName(request.FirstName);
        customer.setLastName(request.LastName);
        customer.setEmail(request.Email);
        customer.setPhone(request.PhoneNumber);
        customer.setEnabled(request.Enabled);
        customer.setDocumentTypeId(request.DocumentTypeId);
        customer.setDocNumber(request.DocNumber);
        customer.setShippingAddress(request.ShippingAddress);
        customer.setBillingAddress(request.BillingAddress);
        customer.setAdditionalData(request.AdditionalData);

        return customer;
    }

    public void addPaymentProfile(Long customerId, String token, String expiration, String brand, Integer last4) {
        CardNetCustomerResponse customer = customers.get(customerId);
        if (customer == null) {
            throw new IllegalArgumentException("Customer not found");
        }

        PaymentProfile profile = new PaymentProfile();
        profile.setPaymentProfileId(paymentProfileSequence.getAndIncrement());
        profile.setToken(token);
        profile.setExpiration(expiration);
        profile.setActive(true);
        profile.setBrand(brand);
        profile.setLast4(last4);

        customer.getPaymentProfiles().add(profile);
    }
}