package com.cardnet.mock.dto.Request;
import com.fasterxml.jackson.annotation.JsonProperty;
public class PaymentProfileDeleteRequest {
    @JsonProperty("PaymentProfileID") public Long PaymentProfileID;
    
    public Long getPaymentProfileId() { return PaymentProfileID; }
}
