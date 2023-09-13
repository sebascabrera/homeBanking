package com.apmindhub.homeBankingmh.services;

import com.apmindhub.homeBankingmh.dtos.ClientDTO;
import com.apmindhub.homeBankingmh.models.Client;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;

import java.util.List;

public interface ClientService {
    List<ClientDTO> getClientsDto();
    Client getClientCurrent(Authentication authentication);
    void saveClient(Client client);
    Client getClientByEmail (String email);
}
