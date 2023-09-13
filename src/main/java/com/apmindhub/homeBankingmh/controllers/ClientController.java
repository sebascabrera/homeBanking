package com.apmindhub.homeBankingmh.controllers;

import com.apmindhub.homeBankingmh.dtos.ClientDTO;
import com.apmindhub.homeBankingmh.models.Client;
import com.apmindhub.homeBankingmh.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api") // Recibe cualquier peticion HTTP
public class ClientController {
    @Autowired
    private PasswordEncoder passwordEncoder;
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
    @RequestMapping(path = "/clients", method = RequestMethod.POST)

    public ResponseEntity<Object> register(

            @RequestParam String firstName, @RequestParam String lastName,

            @RequestParam String email, @RequestParam String password) {

        if (firstName.isBlank() || lastName.isBlank() || email.isBlank() || password.isBlank()) {

            return new ResponseEntity<>("Missing data", HttpStatus.FORBIDDEN);
        }

        if (clientRepository.findByEmail(email) !=  null) {

            return new ResponseEntity<>("Name already in use", HttpStatus.FORBIDDEN);
        }

        clientRepository.save(new Client(firstName, lastName, email, passwordEncoder.encode(password)));

        return new ResponseEntity<>(HttpStatus.CREATED);

    }
}


