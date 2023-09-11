package com.apmindhub.homeBankingmh.controllers;

import com.apmindhub.homeBankingmh.dtos.TransactionDTO;
import com.apmindhub.homeBankingmh.models.Account;
import com.apmindhub.homeBankingmh.models.Client;
import com.apmindhub.homeBankingmh.models.Transaction;
import com.apmindhub.homeBankingmh.repository.AccountRepository;
import com.apmindhub.homeBankingmh.repository.ClientRepository;
import com.apmindhub.homeBankingmh.repository.TransactionRepository;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.apmindhub.homeBankingmh.models.TransactionType;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class TransactionController {

    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private AccountRepository accountRepository;

    @GetMapping("/transaction")
    public Set<TransactionDTO> getTransactions(){
        return transactionRepository.findAll().stream().map(transaction -> new TransactionDTO(transaction)).collect(Collectors.toSet());
    }
    @GetMapping("/transaction/{id}")
    public TransactionDTO getTransaction(@PathVariable Long id){
        return new TransactionDTO(transactionRepository.findById(id).orElse(null));
    }
    @Transactional
    @PostMapping("/transactions")
    public ResponseEntity<Object> createTransaction(@RequestParam Double amount,
                                                    @RequestParam String description,
                                                    @RequestParam String fromAccountNumber,
                                                    @RequestParam String toAccountNumber,
                                                    Authentication authentication) {

        // Verifica solicitud son correctos
        if (description.isEmpty() || fromAccountNumber.isEmpty() || toAccountNumber.isEmpty()) {
            return new ResponseEntity<>("Description, Account number, or Destination Account is empty", HttpStatus.FORBIDDEN);
        }
        if (fromAccountNumber.equals(toAccountNumber)) {
            return new ResponseEntity<>("Can't be the same account", HttpStatus.FORBIDDEN);
        }

        // Obtén el cliente autenticado
        Client clientAuthenticated = clientRepository.findByEmail(authentication.name());

        //verifica si las cuentas existen y pertenecen al cliente autenticado
        Account initialAccount = accountRepository.getAccountByNumber(fromAccountNumber);
        Account accountDestination = accountRepository.getAccountByNumber(toAccountNumber);

        if (!accountRepository.existsByNumber(fromAccountNumber)) {
            return new ResponseEntity<>("Source Account doesn't exist", HttpStatus.FORBIDDEN);
        }
        if (!clientAuthenticated.getAccounts().contains(initialAccount)) {
            return new ResponseEntity<>("The source account does not belong to the authenticated client", HttpStatus.FORBIDDEN);
        }
        if (!accountRepository.existsByNumber(toAccountNumber)) {
            return new ResponseEntity<>("Destination Account doesn't exist", HttpStatus.FORBIDDEN);
        }
        if (amount > initialAccount.getBalance()) {
            return new ResponseEntity<>("You don't have enough funds", HttpStatus.FORBIDDEN);
        }

        // Si todas las comprobaciones son exitosas, transacciones
        Transaction debitTransaction = new Transaction(TransactionType.DEBIT, -amount,
                description + " DEBIT - " + fromAccountNumber, LocalDateTime.now());
        initialAccount.addTransaction(debitTransaction);
        initialAccount.setBalance(initialAccount.getBalance() - amount);
        transactionRepository.save(debitTransaction);
        accountRepository.save(initialAccount);

        Transaction creditTransaction = new Transaction(TransactionType.CREDIT, +amount, description + " CREDIT +" + fromAccountNumber, LocalDateTime.now());
        accountDestination.addTransaction(creditTransaction);
        accountDestination.setBalance(accountDestination.getBalance() + amount);
        transactionRepository.save(creditTransaction);
        accountRepository.save(accountDestination);

        return new ResponseEntity<>("Transaction successful", HttpStatus.CREATED);
    }

}