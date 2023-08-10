package com.apmindhub.homeBankingmh.models;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private Long id;

    private String type;

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
        this.type = type;
        this.amount = amount;
        this.description = description;
        this.date = date;
    }

    public Account getAccountOwner() {
        return accountOwner;
    }
}
