package com.apmindhub.homeBankingmh.controllers;

import com.apmindhub.homeBankingmh.dtos.AccountDTO;
import com.apmindhub.homeBankingmh.models.Account;
import com.apmindhub.homeBankingmh.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class AccountController {
    @Autowired
    private AccountRepository accountRepository;
    @RequestMapping("/accounts")
public List<AccountDTO> getAccount(){
        List<Account> accountList = accountRepository.findAll();
List<AccountDTO> accountDTOList = accountList.stream().map(account -> new AccountDTO(account)).collect(Collectors.toList());
        return accountDTOList;
    }
@RequestMapping("/accounts/{id}")
    public AccountDTO getAccountDTO(@PathVariable Long id){
    return accountRepository.findById(id)
            .map(account -> new AccountDTO(account))
            .orElse(null);
}

}
