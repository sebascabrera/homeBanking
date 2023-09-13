package com.apmindhub.homeBankingmh.services.implementations;

import com.apmindhub.homeBankingmh.dtos.LoanApplicationDTO;
import com.apmindhub.homeBankingmh.dtos.LoanDTO;
import com.apmindhub.homeBankingmh.models.Loan;
import com.apmindhub.homeBankingmh.services.LoanService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoanServicesImplements implements LoanService {


    @Override
    public List<LoanDTO> getLoans() {
        return null;
    }

    @Override
    public Loan getLoanById(LoanApplicationDTO loanApplicationDTO) {
        return null;
    }

    @Override
    public boolean loanExistsById(LoanApplicationDTO loanApplicationDTO) {
        return false;
    }
}
