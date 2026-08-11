package com.cardnet.mock.dto.Request;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.cardnet.mock.dto.Response.CountryDataDo;
import java.math.BigDecimal;
public class CardNetPurchaseRequest {
    @JsonProperty("TrxToken") public String TrxToken;
    @JsonProperty("Order") public String Order;
    @JsonProperty("Capture") public Boolean Capture;
    @JsonProperty("Amount") public BigDecimal Amount;
    @JsonProperty("Currency") public String Currency;
    @JsonProperty("Tip") public BigDecimal Tip;
    @JsonProperty("Installments") public Integer Installments;
    @JsonProperty("Description") public String Description;
    @JsonProperty("Customer") public Object Customer;
    @JsonProperty("UniqueID") public String UniqueID;
    @JsonProperty("AdditionalData") public String AdditionalData;
    @JsonProperty("CustomerUserAgent") public String CustomerUserAgent;
    @JsonProperty("CustomerIP") public String CustomerIP;
    @JsonProperty("DataDo") public CountryDataDo DataDo;
}
