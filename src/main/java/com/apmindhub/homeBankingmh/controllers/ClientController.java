package com.apmindhub.homeBankingmh.controllers;

import com.apmindhub.homeBankingmh.models.Client;
import com.apmindhub.homeBankingmh.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api") // Recibe cualquier peticion HTTP
public class ClientController {

@Autowired
    private ClientRepository clientRepository;
    //services - servelts  Están diseñados para manejar solicitudes y respuestas en el lado del servidor

@RequestMapping("/clients") // Recibe peticiones GET
    public List<Client> getClient(){
    return clientRepository.findAll();
    }

}


