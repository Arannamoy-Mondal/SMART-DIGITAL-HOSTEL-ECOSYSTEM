package com.backend.backend.dto;

import java.math.BigDecimal;

public record TransactionRequest(
    String userName,
    Integer roomNo,
    String transactionType,
    String paymentMethod,
    String paymentPurpose,
    BigDecimal amount
) {

}
