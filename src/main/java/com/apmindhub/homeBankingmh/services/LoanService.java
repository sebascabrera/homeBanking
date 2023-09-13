package com.apmindhub.homeBankingmh.services;

import com.apmindhub.homeBankingmh.dtos.LoanApplicationDTO;
import com.apmindhub.homeBankingmh.dtos.LoanDTO;
import com.apmindhub.homeBankingmh.models.Loan;

import java.util.List;

public interface LoanService {

    public List<LoanDTO> getLoans();
    public Loan getLoanById(LoanApplicationDTO loanApplicationDTO);
    public boolean loanExistsById(LoanApplicationDTO loanApplicationDTO);
}
