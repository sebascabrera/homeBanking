package com.apmindhub.homeBankingmh.services;

import com.apmindhub.homeBankingmh.dtos.TransactionDTO;
import com.apmindhub.homeBankingmh.models.Transaction;

import java.util.List;

public interface TransactionService {

    List<TransactionDTO> getTransactions();
    Transaction getTransactionById (Long id);
    void createdTransaction (Transaction transaction);
}
