package com.apmindhub.homeBankingmh.dtos;

import com.apmindhub.homeBankingmh.models.Transaction;
import com.apmindhub.homeBankingmh.models.TransactionType;

import java.time.LocalDateTime;

public class TransactionDTO {

    private TransactionType type;

    private Double amount;

    private String description;

    private LocalDateTime date;

    public TransactionDTO(Transaction transaction) {
        type = transaction.getType();
        amount = transaction.getAmount();
        description = transaction.getDescription();
        date = transaction.getDate();
    }

    public TransactionType getType() {
        return type;
    }

    public Double getAmount() {
        return amount;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getDate() {
        return date;
    }
}
