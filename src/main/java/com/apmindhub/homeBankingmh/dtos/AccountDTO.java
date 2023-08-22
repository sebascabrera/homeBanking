package com.apmindhub.homeBankingmh.dtos;

import com.apmindhub.homeBankingmh.models.Account;

import java.time.LocalDate;
import java.util.Set;
import java.util.stream.Collectors;

public class AccountDTO {

    private Long id;

    private String number;

    private LocalDate date;

    private Double balance;

    private final Set<TransactionDTO> types;



    public AccountDTO(Account account) {
        id = account.getId();
        number = account.getNumber();
        date = account.getCreationDate();
        balance = account.getBalance();
        types = account.getTransactions().stream()
                .map(transaction -> new TransactionDTO(transaction))
                .collect(Collectors.toSet());;
    }

    public Long getId() {
        return id;
    }

    public String getNumber() {
        return number;
    }

    public LocalDate getDate() {
        return date;
    }

    public Double getBalance() {
        return balance;
    }

    public Set<TransactionDTO> getTypes() {
        return types;
    }
}
