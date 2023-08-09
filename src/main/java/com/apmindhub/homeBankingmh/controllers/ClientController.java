package com.apmindhub.homeBankingmh.controllers;

import com.apmindhub.homeBankingmh.dtos.ClientDTO;
import com.apmindhub.homeBankingmh.models.Client;
import com.apmindhub.homeBankingmh.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api") // Recibe cualquier peticion HTTP
public class ClientController {

@Autowired
    private ClientRepository clientRepository;
    //services - servelts  Están diseñados para manejar solicitudes y respuestas en el lado del servidor

@RequestMapping("/clients") // Recibe peticiones GET
    public List<ClientDTO> getClient(){
    List<Client> listClient = clientRepository.findAll();

    List<ClientDTO> listClientDTO = listClient.stream().map(client -> new ClientDTO(client)).collect(Collectors.toList());


    return listClientDTO;
}
    @RequestMapping("/clients/{id}")

    public ClientDTO getClient(@PathVariable Long id){

        return new ClientDTO( clientRepository.findById(id).orElse(null));

    }
}


