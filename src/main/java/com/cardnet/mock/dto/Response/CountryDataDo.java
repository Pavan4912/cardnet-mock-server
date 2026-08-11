package com.cardnet.mock.dto.Response;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;
public class CountryDataDo {
    @JsonProperty("Invoice") public String Invoice;
    @JsonProperty("Tax") public BigDecimal Tax;
}
