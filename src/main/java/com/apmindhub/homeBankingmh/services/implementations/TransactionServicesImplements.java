package com.apmindhub.homeBankingmh.services.implementations;

import com.apmindhub.homeBankingmh.dtos.TransactionDTO;
import com.apmindhub.homeBankingmh.models.Transaction;
import com.apmindhub.homeBankingmh.repositories.TransactionRepository;
import com.apmindhub.homeBankingmh.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransactionServicesImplements implements TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;
    @Override
    public List<TransactionDTO> getTransactions() {
        return transactionRepository.findAll().stream().map(TransactionDTO::new).collect(Collectors.toList());
    }

    @Override
    public Transaction getTransactionById(Long id) {
        return transactionRepository.findById(id).orElse(null);
    }

    @Override
    public void createdTransaction(Transaction transaction) {
        transactionRepository.save(transaction);
    }

}
