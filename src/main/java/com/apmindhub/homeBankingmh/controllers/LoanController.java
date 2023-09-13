package com.apmindhub.homeBankingmh.controllers;

import com.apmindhub.homeBankingmh.dtos.ClientLoanDTO;
import com.apmindhub.homeBankingmh.dtos.LoanApplicationDTO;
import com.apmindhub.homeBankingmh.dtos.LoanDTO;
import com.apmindhub.homeBankingmh.models.Account;
import com.apmindhub.homeBankingmh.models.Client;
import com.apmindhub.homeBankingmh.services.AccountService;
import com.apmindhub.homeBankingmh.services.ClientService;
import com.apmindhub.homeBankingmh.services.LoanService;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class LoanController {

    @Autowired
    private ClientService clientService;
    @Autowired
    private AccountService accountService;
    @Autowired
    private LoanService loanService;


    @GetMapping("/loans")
    public List<LoanDTO> getLoans(){
        return loanService.getLoans();
    }
    @PostMapping("/loans")
    public ResponseEntity<Object> addLoans(@RequestBody LoanApplicationDTO loanApplicationDTO, Authentication authentication){
        Client clientAuth = clientService.getClientByEmail(authentication.name());
        Account account = accountService.getAccountByNumber(loanApplicationDTO.getToAccountNumber());
    }

}
