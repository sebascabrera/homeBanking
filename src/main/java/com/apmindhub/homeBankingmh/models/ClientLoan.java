package com.apmindhub.homeBankingmh.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;
import java.util.Set;
@Entity
public class ClientLoan {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")

    private Long id;

    private Long amount;

    private Integer payments;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "client")
    private Client client;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "loan")
    private Loan loan;

    public ClientLoan() {
    }

    public ClientLoan(Long amount, Integer payments, Client client, Loan loan ) {
        this.amount = amount;
        this.payments = payments;
        this.client = client;
        this.loan = loan;
    }

    public Long getId() {
        return id;
    }

    public Long getAmount() {
        return amount;
    }
    public Loan getLoan() {
        return loan;
    }

    public Integer getPayments() {
        return payments;
    }

    public Client getClient() {
        return client;
    }
    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public void setPayments(Integer payments) {
        this.payments = payments;
    }

    public void setLoan(Loan loan) {
        this.loan = loan;
    }
    public void setClient(Client client) {
        this.client = client;
    }

}
