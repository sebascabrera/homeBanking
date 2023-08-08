package com.apmindhub.homeBankingmh.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;

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
    @JoinColumn(name = "client_id")
    private Client clientOwner;

    // Constructores
    public Account(){

    };

    public Account(String number, LocalDate creationDate,Double balance ){

        this.number = number;
        this.balance = balance;
        this.creationDate = LocalDate.now();
    }
//  metodos accesores


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

    /*
    public void setClientOwner(Client clientOwner) {
        this.clientOwner = clientOwner;
    }
    */
}
