package com.cardnet.mock.dto;

public record TokenizeRequest(
        String Email,
        String Pan,
        String CVV,
        String Expiration,
        String Titular,
        Long CustomerId
) {
}
