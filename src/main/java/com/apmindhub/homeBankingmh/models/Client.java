package com.apmindhub.homeBankingmh.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Client {
    //Atributos
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")

    private Long id;
    private String firstName;
    private String lastName;
    private String email;

@OneToMany (mappedBy = "clientOwner", fetch = FetchType.EAGER)
private Set<Account> accounts = new HashSet<>();

@OneToMany (mappedBy = "client", fetch= FetchType.EAGER)
private Set<ClientLoan> clientLoans = new HashSet<>();

    //Constructores

    public Client(){

    }
    public Client(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    // Metodos


    public Set<ClientLoan> getClientLoans() {
        return clientLoans;
    }

    public String getLastName() {
        return lastName;
    }

    public Set<Account> getAccounts() {
        return accounts;
    }

    public String getEmail() {
        return email;
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void addAccount(Account account){
        account.setClient(this);
        accounts.add(account);
    }

    public void addClientLoan (ClientLoan clientLoan){
        clientLoan.setClient(this);
        clientLoans.add(clientLoan);
    }




    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }



}
