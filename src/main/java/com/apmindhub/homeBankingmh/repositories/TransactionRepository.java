package com.apmindhub.homeBankingmh.repositories;


import com.apmindhub.homeBankingmh.models.Transaction;
import com.apmindhub.homeBankingmh.models.TransactionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;
@RepositoryRestResource
public interface TransactionRepository extends JpaRepository<Transaction,Long> {
    List<Transaction> findAllByType(TransactionType Type);
}
