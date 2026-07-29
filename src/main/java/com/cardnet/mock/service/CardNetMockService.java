package com.cardnet.mock.service;

import com.cardnet.mock.dto.TokenizeRequest;
import com.cardnet.mock.dto.TokenizeResponse;
import jakarta.enterprise.context.ApplicationScoped;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.Instant;
import java.time.YearMonth;
import java.util.Base64;
import java.util.UUID;

@ApplicationScoped
public class CardNetMockService {
    public TokenizeResponse tokenize(TokenizeRequest request) {

        String[] expiry = request.Expiration().split("/");

        String month = expiry[0];
        String year = "20" + expiry[1];

        validateCardInput(
                request.Pan(),
                month,
                year,
                request.CVV());

        return new TokenizeResponse(
                generateToken(request),
                Instant.now().toString(),
                "Commerce",
                detectScheme(request.Pan()),
                request.Titular(),
                request.Pan().substring(request.Pan().length()-4),
                Integer.parseInt(month),
                Integer.parseInt(expiry[1]),
                null
        );
    }

    private void validateCardInput(
            String cardNumber,
            String expiryMonth,
            String expiryYear,
            String cvv
    ) {
        if (cardNumber == null || !cardNumber.matches("^[0-9]{13,19}$")) {
            throw new IllegalArgumentException("card_number is invalid");
        }

        if (!luhnValid(cardNumber)) {
            throw new IllegalArgumentException("card_number is invalid");
        }

        if (expiryMonth == null || !expiryMonth.matches("^(0[1-9]|1[0-2])$")) {
            throw new IllegalArgumentException("expiry_month is invalid");
        }

        if (expiryYear == null || !expiryYear.matches("^[0-9]{4}$")) {
            throw new IllegalArgumentException("expiry_year is invalid");
        }

        int month = Integer.parseInt(expiryMonth);
        int year = Integer.parseInt(expiryYear);

        YearMonth expiry = YearMonth.of(year, month);
        YearMonth current = YearMonth.now();

        if (expiry.isBefore(current)) {
            throw new IllegalArgumentException("card is expired");
        }

        if (cvv == null || !cvv.matches("^[0-9]{3,4}$")) {
            throw new IllegalArgumentException("cvv is invalid");
        }
    }

    private boolean luhnValid(String number) {
        int sum = 0;
        boolean alternate = false;

        for (int i = number.length() - 1; i >= 0; i--) {
            int n = Character.digit(number.charAt(i), 10);
            if (n < 0) return false;

            if (alternate) {
                n *= 2;
                if (n > 9) n -= 9;
            }

            sum += n;
            alternate = !alternate;
        }

        return sum % 10 == 0;
    }

    private String detectScheme(String cardNumber) {
        if (cardNumber == null || cardNumber.isBlank()) {
            return "UNKNOWN";
        }

        // normalize input (just in case spaces exist)
        cardNumber = cardNumber.replaceAll("\\s+", "");

        // VISA: 4xxxx
        if (cardNumber.startsWith("4")) {
            return "VISA";
        }

        // MASTERCARD: 51–55 + 2221–2720
        if (cardNumber.matches("^(5[1-5]\\d*)")
                || cardNumber.matches("^(222[1-9]|22[3-9]\\d|2[3-6]\\d{2}|27[01]\\d|2720)\\d*")) {
            return "MASTERCARD";
        }

        // AMEX: 34, 37
        if (cardNumber.matches("^(34|37)\\d*")) {
            return "AMEX";
        }

        // DISCOVER: 6011, 644–649, 65, 622126–622925
        if (cardNumber.matches("^(6011\\d*|65\\d*|64[4-9]\\d*|622(12[6-9]|1[3-9]\\d|[2-8]\\d{2}|9[01]\\d|92[0-5])\\d*)")) {
            return "DISCOVER";
        }

        // RUPAY: 60, 65, 81, 82, 508–509, 353–356 (common BIN ranges)
        if (cardNumber.matches("^(60\\d*|65\\d*|81\\d*|82\\d*|508\\d*|509\\d*|353\\d*|354\\d*|355\\d*|356\\d*)")) {
            return "RUPAY";
        }

        // JCB: 3528–3589
        if (cardNumber.matches("^(35(2[8-9]|[3-8]\\d)\\d*)")) {
            return "JCB";
        }

        // DINERS: 300–305, 36, 38–39
        if (cardNumber.matches("^(30[0-5]\\d*|36\\d*|3[89]\\d*)")) {
            return "DINERS";
        }

        return "CARD";
    }

    private String generateToken(TokenizeRequest request) {

        String input = String.join("|",
                request.Pan(),
                request.Expiration(),
                String.valueOf(request.CustomerId())
        );

        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");

            byte[] hash = digest.digest(
                    input.getBytes(StandardCharsets.UTF_8)
            );

            return "CT__" + Base64.getUrlEncoder()
                    .withoutPadding()
                    .encodeToString(hash)
                    .substring(0, 40);

        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException(
                    "Unable to generate token", e);
        }
    }
}
