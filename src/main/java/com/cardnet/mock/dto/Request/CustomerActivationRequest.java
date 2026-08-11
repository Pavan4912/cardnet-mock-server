package com.cardnet.mock.dto.Request;
import com.fasterxml.jackson.annotation.JsonProperty;
public class CustomerActivationRequest {
    @JsonProperty("Token") public String Token;
    @JsonProperty("ActivationCode") public String ActivationCode;
    public String getToken() { return Token; }
    public String getActivationCode() { return ActivationCode; }
}
