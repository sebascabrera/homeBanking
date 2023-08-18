package com.apmindhub.homeBankingmh.models;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private Long id;

    private TransactionType type;

    private Double amount;

    private String description;

    private LocalDateTime date;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "AccountId")
    private Account accountOwner;


    public Transaction(TransactionType debit, Double amount, String debito, LocalDateTime now) {
    }

    public Transaction(Account accountOwner, TransactionType type, Double amount, String description, LocalDateTime date) {
        this.accountOwner = accountOwner;
        this.type = type;
        this.amount = amount;
        this.description = description;
        this.date = date;
    }

    public Account getAccountOwner() {
        return accountOwner;
    }

    public Long getId() {
        return id;
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

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public void setAccountOwner(Account accountOwner) {
        this.accountOwner = accountOwner;
    }
}