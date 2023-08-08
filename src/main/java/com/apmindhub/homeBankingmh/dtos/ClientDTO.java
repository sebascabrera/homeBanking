package com.apmindhub.homeBankingmh.dtos;

import com.apmindhub.homeBankingmh.models.Client;

public class ClientDTO {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;

    public ClientDTO(Client client) {
       // this.id= client.getID();
        this.firstName = client.getFirstName();
        this.lastName = client.getLastName();
        this.email = client.getEmail();
    }

    // getters
}




