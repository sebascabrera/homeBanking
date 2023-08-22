package com.apmindhub.homeBankingmh.dtos;

import com.apmindhub.homeBankingmh.models.Client;

import java.util.Set;
import java.util.stream.Collectors;

public class ClientDTO {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;

    private Set<AccountDTO> accounts;

    private Set<ClientLoanDTO> loans; // Propiedad Loans para "mappear"

    public ClientDTO(Client client) {
        id = client.getId();
        firstName = client.getFirstName();
        lastName = client.getLastName();
        email = client.getEmail();
        accounts = client.getAccounts().stream()
                .map(account -> new AccountDTO(account))
                .collect(Collectors.toSet());
        loans = client.getClientLoans().stream().map(loansToDTO -> new ClientLoanDTO(loansToDTO)).collect(Collectors.toSet());
    }
    // getters


    public Set<AccountDTO> getAccounts() {
        return accounts;
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public Set<ClientLoanDTO> getLoansDTO() {
        return loans;
    }
}




