package com.apmindhub.homeBankingmh.repository;

import com.apmindhub.homeBankingmh.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface ClientRepository extends JpaRepository<Client,Long> {
    Client findByEmail(String email);
    /*.orElse(null) Se puede agregar??*/
}
