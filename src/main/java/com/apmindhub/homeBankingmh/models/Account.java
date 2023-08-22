package com.apmindhub.homeBankingmh.models;

import com.apmindhub.homeBankingmh.dtos.AccountDTO;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")

    private Long id;

    private String number;

    private LocalDate creationDate;

    private Double balance;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "clientId")
    private Client clientOwner;

    @OneToMany (mappedBy = "accountOwner", fetch = FetchType.EAGER)
    private Set<Transaction> transactions = new HashSet<>();

    // Constructores
    public Account(){

    }

    public Account(String number, LocalDate creationDate,Double balance ){
        this.number = number;
        this.balance = balance;
        this.creationDate = creationDate;
    }
//  metodos accesores


    public Set<Transaction> getTransactions() {
        return transactions;
    }

    public Long getId() {
        return id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

       public Double getBalance() {
        return balance;
    }

    public Client getClientOwner() {
        return clientOwner;
    }

    public void setClient(Client client) {
        this.clientOwner= client;
    }

    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
        transaction.setAccountOwner(this);
    }

}
