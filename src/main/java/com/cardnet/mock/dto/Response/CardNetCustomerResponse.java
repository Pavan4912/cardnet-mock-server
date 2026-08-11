package com.cardnet.mock.dto.Response;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
public class CardNetCustomerResponse {
    @JsonProperty("CustomerId") public Long CustomerId;
    @JsonProperty("CommerceCustomerId") public String CommerceCustomerId;
    @JsonProperty("Created") public String Created;
    @JsonProperty("Owner") public String Owner;
    @JsonProperty("FirstName") public String FirstName;
    @JsonProperty("LastName") public String LastName;
    @JsonProperty("Email") public String Email;
    @JsonProperty("PhoneNumber") public String PhoneNumber;
    @JsonProperty("Enabled") public Boolean Enabled;
    @JsonProperty("ShippingAddress") public AddressDto ShippingAddress;
    @JsonProperty("BillingAddress") public AddressDto BillingAddress;
    @JsonProperty("AdditionalData") public String AdditionalData;
    @JsonProperty("PaymentProfiles") public List<PaymentProfile> PaymentProfiles;
    @JsonProperty("CaptureURL") public String CaptureURL;
    @JsonProperty("UniqueID") public String UniqueID;
    @JsonProperty("URL") public String URL;
    @JsonProperty("DocumentTypeId") public Integer DocumentTypeId;
    @JsonProperty("DocNumber") public String DocNumber;
    
    // For service backwards compatibility
    public void setCustomerId(Long id) { this.CustomerId = id; }
    public Long getCustomerId() { return CustomerId; }
    public void setFirstName(String f) { this.FirstName = f; }
    public void setLastName(String l) { this.LastName = l; }
    public void setEmail(String e) { this.Email = e; }
    public void setPhone(String p) { this.PhoneNumber = p; }
    public void setCardNetCustomerId(Long c) {}
    public void setPaymentProfiles(List<PaymentProfile> p) { this.PaymentProfiles = p; }
    public List<PaymentProfile> getPaymentProfiles() { return PaymentProfiles; }
    public void setCommerceCustomerId(String c) { this.CommerceCustomerId = c; }
    public void setEnabled(Boolean e) { this.Enabled = e; }
    public void setDocumentTypeId(Integer d) { this.DocumentTypeId = d; }
    public void setDocNumber(String d) { this.DocNumber = d; }
    public void setShippingAddress(AddressDto s) { this.ShippingAddress = s; }
    public void setBillingAddress(AddressDto b) { this.BillingAddress = b; }
    public void setAdditionalData(String a) { this.AdditionalData = a; }
}
