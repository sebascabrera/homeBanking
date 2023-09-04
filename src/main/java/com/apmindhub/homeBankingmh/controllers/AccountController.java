package com.apmindhub.homeBankingmh.controllers;

import com.apmindhub.homeBankingmh.dtos.AccountDTO;
import com.apmindhub.homeBankingmh.models.Account;
import com.apmindhub.homeBankingmh.models.Client;
import com.apmindhub.homeBankingmh.repository.AccountRepository;
import com.apmindhub.homeBankingmh.repository.ClientRepository;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import static com.apmindhub.homeBankingmh.utils.Util.getRandomNumber;

@RestController
@RequestMapping("/api")
public class AccountController {
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private ClientRepository clientRepository;
    @RequestMapping("/accounts")
    public List<AccountDTO> getAccount(){
        List<Account> accountList = accountRepository.findAll();
        List<AccountDTO> accountDTOList = accountList.stream()
                .map(account -> new AccountDTO(account))
                .collect(Collectors.toList());
        return accountDTOList;
    }
    @RequestMapping("/accounts/{id}")
    public AccountDTO getAccountDTO(@PathVariable Long id){
        return accountRepository.findById(id)
                .map(account -> new AccountDTO(account))
                .orElse(null);
    }
    @RequestMapping(value = "/clients/current/accounts", method = RequestMethod.POST)
    public ResponseEntity<Object> createdAccount (Authentication authentication){
        Client clientAuth =  clientRepository.findByEmail(authentication.name());
        if (clientAuth.getAccounts().stream().count()==3){
            System.out.println("You have 3 accounts, that´s all folks!.");
            return new ResponseEntity<>("Max number of accounts", HttpStatus.FORBIDDEN);
        }
        Account account = null;
        do {
            String number = "VIN" + getRandomNumber(10000000,99999999);
            account= new Account(number,LocalDate.now(),0.0);
        }
        while(accountRepository.existsByNumber(account.getNumber()));
        clientAuth.addAccount(account);
        accountRepository.save(account);
        System.out.println("New account created");
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
      }
