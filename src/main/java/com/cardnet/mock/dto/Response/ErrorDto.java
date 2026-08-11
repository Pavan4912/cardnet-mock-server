package com.cardnet.mock.dto.Response;
import com.fasterxml.jackson.annotation.JsonProperty;
public class ErrorDto {
    @JsonProperty("Code") public String Code;
    @JsonProperty("Message") public String Message;
    public ErrorDto() {}
    public ErrorDto(String code, String message) { this.Code = code; this.Message = message; }
}
