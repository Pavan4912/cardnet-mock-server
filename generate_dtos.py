import os

base_dir = r"C:\CardnetmockServer\cardnet-mock-server\src\main\java\com\cardnet\mock\dto"
req_dir = os.path.join(base_dir, "Request")
res_dir = os.path.join(base_dir, "Response")

os.makedirs(req_dir, exist_ok=True)
os.makedirs(res_dir, exist_ok=True)

models = {
    "Response/ErrorDto.java": """package com.cardnet.mock.dto.Response;
import com.fasterxml.jackson.annotation.JsonProperty;
public class ErrorDto {
    @JsonProperty("Code") public String Code;
    @JsonProperty("Message") public String Message;
    public ErrorDto() {}
    public ErrorDto(String code, String message) { this.Code = code; this.Message = message; }
}
""",
    "Response/AddressDto.java": """package com.cardnet.mock.dto.Response;
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
""",
    "Response/CountryDataDo.java": """package com.cardnet.mock.dto.Response;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;
public class CountryDataDo {
    @JsonProperty("Invoice") public String Invoice;
    @JsonProperty("Tax") public BigDecimal Tax;
}
""",
    "Response/TransactionStep.java": """package com.cardnet.mock.dto.Response;
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
""",
    "Response/Transaction.java": """package com.cardnet.mock.dto.Response;
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
""",
    "Response/PaymentProfile.java": """package com.cardnet.mock.dto.Response;
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
    public Long getPaymentProfileId() { return PaymentProfileId; }
    public void setPaymentProfileId(Long id) { this.PaymentProfileId = id; }
    public void setToken(String t) { this.Token = t; }
    public void setActive(Boolean a) { this.Enabled = a; }
    public void setExpiration(String e) { this.Expiration = e; }
}
""",
    "Response/CardNetCustomerResponse.java": """package com.cardnet.mock.dto.Response;
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
""",
    "Response/CardNetPurchaseResponse.java": """package com.cardnet.mock.dto.Response;
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
""",
    "Response/TokenizeResponse.java": """package com.cardnet.mock.dto.Response;
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
""",
    "Request/CardNetCustomerRequest.java": """package com.cardnet.mock.dto.Request;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.cardnet.mock.dto.Response.AddressDto;
public class CardNetCustomerRequest {
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
    
    public String getFirstName() { return FirstName; }
    public String getLastName() { return LastName; }
    public String getEmail() { return Email; }
    public String getPhone() { return PhoneNumber; }
}
""",
    "Request/CustomerUpdateRequest.java": """package com.cardnet.mock.dto.Request;
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
""",
    "Request/CustomerActivationRequest.java": """package com.cardnet.mock.dto.Request;
import com.fasterxml.jackson.annotation.JsonProperty;
public class CustomerActivationRequest {
    @JsonProperty("Token") public String Token;
    @JsonProperty("ActivationCode") public String ActivationCode;
    public String getToken() { return Token; }
    public String getActivationCode() { return ActivationCode; }
}
""",
    "Request/PaymentProfileUpdateRequest.java": """package com.cardnet.mock.dto.Request;
import com.fasterxml.jackson.annotation.JsonProperty;
public class PaymentProfileUpdateRequest {
    @JsonProperty("PaymentProfileID") public Long PaymentProfileID;
    @JsonProperty("Expiration") public String Expiration;
    @JsonProperty("Enable") public Boolean Enable;
    
    public Long getPaymentProfileId() { return PaymentProfileID; }
    public String getExpiration() { return Expiration; }
    public Boolean getEnable() { return Enable; }
}
""",
    "Request/PaymentProfileDeleteRequest.java": """package com.cardnet.mock.dto.Request;
import com.fasterxml.jackson.annotation.JsonProperty;
public class PaymentProfileDeleteRequest {
    @JsonProperty("PaymentProfileID") public Long PaymentProfileID;
    
    public Long getPaymentProfileId() { return PaymentProfileID; }
}
""",
    "Request/CardNetPurchaseRequest.java": """package com.cardnet.mock.dto.Request;
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
""",
    "Request/TokenizeRequest.java": """package com.cardnet.mock.dto.Request;
import com.fasterxml.jackson.annotation.JsonProperty;
public record TokenizeRequest(
    @JsonProperty("Email") String Email,
    @JsonProperty("Pan") String Pan,
    @JsonProperty("CVV") String CVV,
    @JsonProperty("Expiration") String Expiration,
    @JsonProperty("Titular") String Titular,
    @JsonProperty("CustomerId") Long CustomerId
) {}
"""
}

for name, content in models.items():
    path = os.path.join(base_dir, name.replace("/", os.sep))
    with open(path, "w", encoding="utf-8") as f:
        f.write(content)

print("Generated DTOs successfully.")
