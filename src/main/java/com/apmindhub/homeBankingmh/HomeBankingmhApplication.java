package com.apmindhub.homeBankingmh;

import com.apmindhub.homeBankingmh.models.*;
import com.apmindhub.homeBankingmh.repository.AccountRepository;
import com.apmindhub.homeBankingmh.repository.ClientRepository;
import com.apmindhub.homeBankingmh.repository.LoanRepository;
import com.apmindhub.homeBankingmh.repository.TransactionRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;

import java.time.LocalDateTime;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@SpringBootApplication
public class HomeBankingmhApplication {

	public static void main(String[] args) {
		SpringApplication.run(HomeBankingmhApplication.class, args);
	}
	@Bean
	public CommandLineRunner init(ClientRepository clientRepository, AccountRepository accountRepository, TransactionRepository transactionRepository, LoanRepository loanRepository){
		return args -> {
			Client client1 = new Client("Melba", "Morel", "melba@mindhub.com" );
			Client client2 = new Client("Jack", "Bauer", "jackbauer@gmail.com");
			Client client3 = new Client("Chloe", "O'Brian", "cloeobrian@hotmail.com");
			clientRepository.save(client1);
			clientRepository.save(client2);
			clientRepository.save(client3);

			Account account1 = new Account(client1, "VIN001", LocalDate.now(), 5000.0);
			Account account2 = new Account(client2, "VIN002", LocalDate.now().plus(1, ChronoUnit.DAYS), 7500.0);

			accountRepository.save(account1);
			accountRepository.save(account2);
			Transaction transaction1, transaction2 ;
			transaction1 = new Transaction(TransactionType.Debit, -5000.00, "Debito", LocalDateTime.now());
			transaction2 = new Transaction(TransactionType.Credit, 7000.00, "Credito", LocalDateTime.now());
			transactionRepository.save(transaction1);
			transactionRepository.save(transaction2);

			Loan loan1 = new Loan("Hipotecario", 500000L, List.of(12,24,36,48,60));
			Loan loan2 = new Loan("Personal", 100000L, List.of( 6,12,24));
			Loan loan3 = new Loan("Automotriz", 300000L, List.of( 6,12,24,36));
			loanRepository.save(loan1);
			loanRepository.save(loan2);
			loanRepository.save(loan3);
		};
	}
}
