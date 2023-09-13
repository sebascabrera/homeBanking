package com.apmindhub.homeBankingmh.services;

import com.apmindhub.homeBankingmh.dtos.AccountDTO;
import com.apmindhub.homeBankingmh.models.Account;

import java.util.Set;

public interface AccountService {
    Set<AccountDTO> getAccountsDto();
    AccountDTO getAccountDtoById(Long id);
    boolean existsByNumber(String number);
    void saveAccount(Account account);
    Account getAccountByNumber(String number);
}
