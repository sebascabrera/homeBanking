package com.apmindhub.homeBankingmh.services.implementations;

import com.apmindhub.homeBankingmh.dtos.ClientDTO;
import com.apmindhub.homeBankingmh.models.Client;
import com.apmindhub.homeBankingmh.repositories.ClientRepository;
import com.apmindhub.homeBankingmh.services.ClientService;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class ClientServicesImplements implements ClientService {


    Autowired
    private ClientRepository clientRepository;
    @Override
    public List<ClientDTO> getClientsDto() {
       // return clientRepository.findAll().stream().map(client -> new ClientDTO(client)).collect(toList());
        return clientRepository.findAll().stream().map(client -> new ClientDTO(client)).collect(toList());
    }

    @Override
    public Client getClientCurrent(Authentication authentication) {
        return clientRepository.findByEmail(authentication.name());
    }

    @Override
    public void saveClient(Client client) {
        clientRepository.save(client);
    }

    @Override
    public Client getClientByEmail(String email) {
        return clientRepository.findByEmail(email);
    }
}
