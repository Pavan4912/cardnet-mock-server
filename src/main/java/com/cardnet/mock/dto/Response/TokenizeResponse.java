package com.cardnet.mock.dto.Response;
import com.fasterxml.jackson.annotation.JsonProperty;
public record TokenizeResponse(
    @JsonProperty("TokenId") String TokenId,
    @JsonProperty("Created") String Created,
    @JsonProperty("Type") String Type,
    @JsonProperty("Brand") String Brand,
    @JsonProperty("IssuerBank") String IssuerBank,
    @JsonProperty("Owner") String Owner,
    @JsonProperty("Last4") String Last4,
    @JsonProperty("CardExpMonth") Integer CardExpMonth,
    @JsonProperty("CardExpYear") Integer CardExpYear,
    @JsonProperty("URL") String URL,
    @JsonProperty("Error") String Error
) {}
