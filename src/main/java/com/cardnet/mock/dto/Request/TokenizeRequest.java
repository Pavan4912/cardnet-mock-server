package com.cardnet.mock.dto.Request;
import com.fasterxml.jackson.annotation.JsonProperty;
public record TokenizeRequest(
    @JsonProperty("Email") String Email,
    @JsonProperty("Pan") String Pan,
    @JsonProperty("CVV") String CVV,
    @JsonProperty("Expiration") String Expiration,
    @JsonProperty("Titular") String Titular,
    @JsonProperty("CustomerId") Long CustomerId
) {}
