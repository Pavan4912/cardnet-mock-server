package com.cardnet.mock.dto.Response;
import com.fasterxml.jackson.annotation.JsonProperty;
public class AddressDto {
    @JsonProperty("AddressID") public Long AddressID;
    @JsonProperty("AddressType") public String AddressType;
    @JsonProperty("Country") public String Country;
    @JsonProperty("State") public String State;
    @JsonProperty("City") public String City;
    @JsonProperty("AddressDetail") public String AddressDetail;
    @JsonProperty("PostalCode") public String PostalCode;
}
