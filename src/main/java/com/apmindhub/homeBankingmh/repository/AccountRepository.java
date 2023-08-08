package com.apmindhub.homeBankingmh.repository;

import com.apmindhub.homeBankingmh.models.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface AccountRepository extends JpaRepository<Account, Long> {
    List<Account> findAllByNumber(String number);
}
