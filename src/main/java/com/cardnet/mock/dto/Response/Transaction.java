package com.cardnet.mock.dto.Response;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
public class Transaction {
    @JsonProperty("TransactionID") public Long TransactionID;
    @JsonProperty("Created") public String Created;
    @JsonProperty("TransactionStatusId") public Integer TransactionStatusId;
    @JsonProperty("Status") public String Status;
    @JsonProperty("Description") public String Description;
    @JsonProperty("ApprovalCode") public String ApprovalCode;
    @JsonProperty("Steps") public List<TransactionStep> Steps;
}
