package com.cardnet.mock.dto.Request;
import com.fasterxml.jackson.annotation.JsonProperty;
public class PaymentProfileUpdateRequest {
    @JsonProperty("PaymentProfileID") public Long PaymentProfileID;
    @JsonProperty("Expiration") public String Expiration;
    @JsonProperty("Enable") public Boolean Enable;
    
    public Long getPaymentProfileId() { return PaymentProfileID; }
    public String getExpiration() { return Expiration; }
    public Boolean getEnable() { return Enable; }
}
