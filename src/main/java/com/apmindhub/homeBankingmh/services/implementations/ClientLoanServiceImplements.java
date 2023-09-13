package com.apmindhub.homeBankingmh.services.implementations;

import com.apmindhub.homeBankingmh.models.ClientLoan;
import com.apmindhub.homeBankingmh.repositories.ClientLoanRepository;
import com.apmindhub.homeBankingmh.services.ClientLoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientLoanServiceImplements implements ClientLoanService {
    @Autowired
    private ClientLoanRepository clientLoanRepository;
    @Override
    public void saveClientLoan(ClientLoan clientLoan) {
                clientLoanRepository.save(clientLoan);
    }

}
