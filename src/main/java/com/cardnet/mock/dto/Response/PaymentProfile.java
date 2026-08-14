package com.cardnet.mock.dto.Response;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
public class PaymentProfile {
    @JsonProperty("PaymentProfileId") public Long PaymentProfileId;
    @JsonProperty("PaymentMediaId") public Long PaymentMediaId;
    @JsonProperty("Brand") public String Brand;
    @JsonProperty("IssuerBank") public String IssuerBank;
    @JsonProperty("Type") public String Type;
    @JsonProperty("Token") public String Token;
    @JsonProperty("Expiration") public String Expiration;
    @JsonProperty("Last4") public Integer Last4;
    @JsonProperty("Enabled") public Boolean Enabled;
    
    // backwards compatibility for the mock service
    @JsonIgnore
    public Long getPaymentProfileId() { return PaymentProfileId; }
    public void setPaymentProfileId(Long id) { this.PaymentProfileId = id; }
    public void setToken(String t) { this.Token = t; }
    public void setActive(Boolean a) { this.Enabled = a; }
    public void setExpiration(String e) { this.Expiration = e; }
    public void setBrand(String s) { this.Brand = s; }
    public void setLast4(Integer l) { this.Last4 = l; }
}
