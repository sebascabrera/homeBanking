package com.apmindhub.homeBankingmh.controllers;

import com.apmindhub.homeBankingmh.dtos.AccountDTO;
import com.apmindhub.homeBankingmh.models.Account;
import com.apmindhub.homeBankingmh.models.Client;
import com.apmindhub.homeBankingmh.repositories.AccountRepository;
import com.apmindhub.homeBankingmh.repositories.ClientRepository;
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

    @PostMapping("/clients/current/accounts")
    public ResponseEntity<Object> createAccount(Authentication authentication) {
        Client clientAuth = clientRepository.findByEmail(authentication.name());
        if (clientAuth.getAccounts().size() >= 3) {
            return new ResponseEntity<>("You have reached the maximum number of accounts.", HttpStatus.FORBIDDEN);
        }

        String number;
        do {
            number = "VIN" + getRandomNumber(10000000, 99999999);
        } while (accountRepository.existsByNumber(number));

        Account account = new Account(number, LocalDate.now(), 0.0);
        clientAuth.addAccount(account);
        accountRepository.save(account);
        return new ResponseEntity<>("New account created", HttpStatus.CREATED);
    }
      }
