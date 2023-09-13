package com.apmindhub.homeBankingmh.controllers;

import com.apmindhub.homeBankingmh.dtos.ClientLoanDTO;
import com.apmindhub.homeBankingmh.dtos.LoanApplicationDTO;
import com.apmindhub.homeBankingmh.dtos.LoanDTO;
import com.apmindhub.homeBankingmh.models.*;
import com.apmindhub.homeBankingmh.services.AccountService;
import com.apmindhub.homeBankingmh.services.ClientService;
import com.apmindhub.homeBankingmh.services.LoanService;
import com.apmindhub.homeBankingmh.services.ClientLoanService;
import com.apmindhub.homeBankingmh.services.TransactionService;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
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
    @Autowired
    private ClientLoanService clientLoanService;
    @Autowired
    private TransactionService transactionService;




    @GetMapping("/loans")
    public List<LoanDTO> getLoans(){
        return loanService.getLoans();
    }
    @PostMapping("/loans")
    public ResponseEntity<Object> addLoans(@RequestBody LoanApplicationDTO loanApplicationDTO, Authentication authentication){
        Client clientAuth = clientService.getClientByEmail(authentication.name());
        Account account = accountService.getAccountByNumber(loanApplicationDTO.getToAccountNumber());

        Loan loan = loanService.getLoanById(loanApplicationDTO);
        Double amount = loanApplicationDTO.getAmount();
        Integer payments = loanApplicationDTO.getPayments();

        if (loanApplicationDTO.getLoanId() == null){
            return new ResponseEntity<>("Missing data,loan is required", HttpStatus.FORBIDDEN);
        }
        if(!loanService.loanExistsById(loanApplicationDTO)){
            return new ResponseEntity<>("Missing data,loan is not Exists", HttpStatus.FORBIDDEN);
        }

        if (loanApplicationDTO.getAmount() <= 0){
            return new ResponseEntity<>("The amount must be over zero", HttpStatus.FORBIDDEN);
        } else if (loanApplicationDTO.getAmount() > loan.getMaxAmount()) {
            return new ResponseEntity<>("The amount mustn't exceed $" + loan.getMaxAmount(), HttpStatus.FORBIDDEN);
        }
        if (!accountService.existsByNumber(account.getNumber())){
            return new ResponseEntity<>("This Account doesn't Exists ",HttpStatus.FORBIDDEN);
        }
        if (!clientAuth.getAccounts().contains(account)){
            return new ResponseEntity<>("This Account doesn't belong to a client ",HttpStatus.FORBIDDEN);
        }

        ClientLoan clientLoan = new ClientLoan((long) (amount*1.2), payments);
        loan.addClientLoans(clientLoan);
        clientAuth.addClientLoans(clientLoan);
        clientLoanService.saveClientLoan(clientLoan);

        Transaction transactionCredit = new Transaction(TransactionType.CREDIT, amount, loan.getName() + " Loan approved", LocalDateTime.now());
        account.addTransaction(transactionCredit);

        account.setBalance(account.getBalance()+amount);
        transactionService.createdTransaction(transactionCredit);
        accountService.saveAccount(account);

        return new ResponseEntity<>("Here you have your Loan", HttpStatus.CREATED);
    }

}
