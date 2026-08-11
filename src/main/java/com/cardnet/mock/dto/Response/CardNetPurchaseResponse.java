package com.cardnet.mock.dto.Response;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;
import java.util.List;
public class CardNetPurchaseResponse {
    @JsonProperty("PurchaseId") public Long PurchaseId;
    @JsonProperty("Created") public String Created;
    @JsonProperty("TrxToken") public String TrxToken;
    @JsonProperty("Order") public String Order;
    @JsonProperty("Transaction") public Transaction Transaction;
    @JsonProperty("Capture") public Boolean Capture;
    @JsonProperty("Amount") public BigDecimal Amount;
    @JsonProperty("Currency") public String Currency;
    @JsonProperty("Tip") public BigDecimal Tip;
    @JsonProperty("Installments") public Integer Installments;
    @JsonProperty("Description") public String Description;
    @JsonProperty("Customer") public CardNetCustomerResponse Customer;
    @JsonProperty("UniqueID") public String UniqueID;
    @JsonProperty("AdditionalData") public String AdditionalData;
    @JsonProperty("CustomerUserAgent") public String CustomerUserAgent;
    @JsonProperty("CustomerIP") public String CustomerIP;
    @JsonProperty("DataDo") public CountryDataDo DataDo;
    @JsonProperty("URL") public String URL;
    
    // For Service backwards compatibility
    public void setPurchaseId(String p) { this.PurchaseId = Long.parseLong(p); }
    public void setResponseCode(String r) { if(this.Transaction==null) this.Transaction=new Transaction(); this.Transaction.ApprovalCode=r; }
    public void setResponseMessage(String r) { if(this.Transaction==null) this.Transaction=new Transaction(); this.Transaction.Description=r; }
    public void setStatus(String s) { if(this.Transaction==null) this.Transaction=new Transaction(); this.Transaction.Status=s; }
}
