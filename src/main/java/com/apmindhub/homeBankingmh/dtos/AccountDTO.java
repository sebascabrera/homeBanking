package com.apmindhub.homeBankingmh.dtos;

import com.apmindhub.homeBankingmh.models.Account;
import com.apmindhub.homeBankingmh.models.Transaction;

import java.time.LocalDate;
import java.util.Set;
import java.util.stream.Collectors;

public class AccountDTO {

    private Long id;

    private String number;

    private LocalDate creationDate = LocalDate.now();

    private Double balance;

    private final Set<TransactionDTO> types;



    public AccountDTO(Account account) {
        id = account.getId();
        number = account.getNumber();
        creationDate = account.getCreationDate();
        balance = account.getBalance();
        types = account.getTransactions().stream()
                .map(transaction -> new TransactionDTO(transaction))
                .collect(Collectors.toSet());;
    }

    public Long getId() {
        return id;
    }

    public Set<TransactionDTO> getType() {
        return types;
    }

    public String getNumber() {
        return number;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public Double getBalance() {
        return balance;
    }
}
