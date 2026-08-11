package com.cardnet.mock.dto.Request;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.cardnet.mock.dto.Response.AddressDto;
public class CustomerUpdateRequest {
    @JsonProperty("CommerceCustomerId") public String CommerceCustomerId;
    @JsonProperty("FirstName") public String FirstName;
    @JsonProperty("LastName") public String LastName;
    @JsonProperty("Email") public String Email;
    @JsonProperty("PhoneNumber") public String PhoneNumber;
    @JsonProperty("Enabled") public Boolean Enabled;
    @JsonProperty("ShippingAddress") public AddressDto ShippingAddress;
    @JsonProperty("BillingAddress") public AddressDto BillingAddress;
    @JsonProperty("AdditionalData") public String AdditionalData;
    @JsonProperty("DocumentTypeId") public Integer DocumentTypeId;
    @JsonProperty("DocNumber") public String DocNumber;
    
    public String getCommerceCustomerId() { return CommerceCustomerId; }
    public String getFirstName() { return FirstName; }
    public String getLastName() { return LastName; }
    public String getEmail() { return Email; }
    public String getPhone() { return PhoneNumber; }
    public Boolean getEnabled() { return Enabled; }
    public Integer getDocumentTypeId() { return DocumentTypeId; }
    public String getDocNumber() { return DocNumber; }
    public AddressDto getShippingAddress() { return ShippingAddress; }
    public AddressDto getBillingAddress() { return BillingAddress; }
    public String getAdditionalData() { return AdditionalData; }
}
