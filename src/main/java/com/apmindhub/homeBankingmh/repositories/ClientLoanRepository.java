package com.apmindhub.homeBankingmh.repositories;

import com.apmindhub.homeBankingmh.models.ClientLoan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClientLoanRepository extends JpaRepository<ClientLoan, Long> {
    List<ClientLoan> findAllById(Long id);
}
