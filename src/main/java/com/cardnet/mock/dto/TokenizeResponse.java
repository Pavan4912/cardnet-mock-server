package com.cardnet.mock.dto;

public record TokenizeResponse(
        String TokenId,
        String Created,
        String Type,
        String Brand,
        String Owner,
        String Last4,
        Integer CardExpMonth,
        Integer CardExpYear,
        String Error
) {
}
