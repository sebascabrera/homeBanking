package com.apmindhub.homeBankingmh.services.implementations;

import com.apmindhub.homeBankingmh.dtos.AccountDTO;
import com.apmindhub.homeBankingmh.models.Account;
import com.apmindhub.homeBankingmh.repositories.AccountRepository;
import com.apmindhub.homeBankingmh.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;

import static java.util.stream.Collectors.toSet;

public class AccountServicesImplements implements AccountService {

    @Autowired
    private AccountRepository accountRepository;
    @Override
    public Set<AccountDTO> getAccountsDto() {
        return accountRepository.findAll().stream().map(account -> new AccountDTO(account)).collect(toSet());
    }

    @Override
    public AccountDTO getAccountDtoById(Long id) {
        return accountRepository.findById(id).map(account -> new AccountDTO(account)).orElse(null);
    }

    @Override
    public boolean existsByNumber(String number) {
        return accountRepository.existsByNumber(number);
    }

    @Override
    public void saveAccount(Account account) {
        accountRepository.save(account);
    }

    @Override
    public Account getAccountByNumber(String number) {
        return accountRepository.getAccountByNumber(number);
    }
}
