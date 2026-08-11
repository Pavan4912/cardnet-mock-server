package com.cardnet.mock.dto.Response;
import com.fasterxml.jackson.annotation.JsonProperty;
public class TransactionStep {
    @JsonProperty("Step") public String Step;
    @JsonProperty("Created") public String Created;
    @JsonProperty("Status") public Integer Status;
    @JsonProperty("ResponseCode") public String ResponseCode;
    @JsonProperty("ResponseMessage") public String ResponseMessage;
    @JsonProperty("Error") public String Error;
    @JsonProperty("AuthorizationCode") public String AuthorizationCode;
    @JsonProperty("UniqueId") public String UniqueId;
}
