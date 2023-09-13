package com.apmindhub.homeBankingmh.repositories;


import com.apmindhub.homeBankingmh.models.Loan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoanRepository extends JpaRepository<Loan, Long> {

}
