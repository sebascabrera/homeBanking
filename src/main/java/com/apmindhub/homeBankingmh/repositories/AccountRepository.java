package com.apmindhub.homeBankingmh.repositories;

import com.apmindhub.homeBankingmh.models.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface AccountRepository extends JpaRepository<Account, Long> {
    List<Account> findAllById(Long id);

    boolean existsByNumber(String number);
    Account getAccountByNumber(String number);
}
