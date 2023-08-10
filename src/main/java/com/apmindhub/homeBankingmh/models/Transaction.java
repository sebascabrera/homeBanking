package com.apmindhub.homeBankingmh.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private Long id;

    private TransactionType type;

    private String amount;

    private String description;

    private String date;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "AccountId")
    private Account accountOwner;


    public Transaction() {
    }

    public Transaction(Account accountOwner, String type, String amount, String description, String date) {
        this.accountOwner = accountOwner;
        this.type = TransactionType.valueOf(type);
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

    public String getAmount() {
        return amount;
    }

    public String getDescription() {
        return description;
    }

    public String getDate() {
        return date;
    }

/*    public void setType(String type) {
        this.type = type;
    }*/

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setAccountOwner(Account accountOwner) {
        this.accountOwner = accountOwner;
    }
}