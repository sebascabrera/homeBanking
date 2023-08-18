package com.apmindhub.homeBankingmh.repository;


import com.apmindhub.homeBankingmh.models.Loan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LoanRepository extends JpaRepository<Loan, Long> {

    List<Loan> findAllById(Long id);
}
