package com.apmindhub.homeBankingmh;

import com.apmindhub.homeBankingmh.models.*;
import com.apmindhub.homeBankingmh.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;

import java.time.LocalDateTime;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@SpringBootApplication
public class HomeBankingmhApplication {
	@Autowired
	private PasswordEncoder passwordEncoder;
	public static void main(String[] args) {
		SpringApplication.run(HomeBankingmhApplication.class, args);
	}

	@Bean
	public CommandLineRunner init(ClientRepository clientRepository, AccountRepository accountRepository,
								  TransactionRepository transactionRepository, LoanRepository loanRepository,
								  ClientLoanRepository clientLoanRepository, CardRepository cardRepository){
		return args -> {
			Client client1 = new Client("Melba", "Morel", "melba@mindhub.com",passwordEncoder.encode( "Morel") );
			Client client2 = new Client("Jack", "Bauer", "jackbauer@gmail.com",passwordEncoder.encode ("Bauer"));
			Client client3 = new Client("Chloe", "O'Brian", "cloeobrian@hotmail.com",passwordEncoder.encode ("Obrian"));
			Client client4 = new Client("Enzo", "Perez", "enzoperez189@gmail.com", passwordEncoder.encode("ADMIN"));
			clientRepository.save(client1);
			clientRepository.save(client2);
			clientRepository.save(client3);
			clientRepository.save(client4);

			Account account1 = new Account( "VIN001", LocalDate.now(), 5000.0);
			Account account2 = new Account( "VIN002", LocalDate.now().plusDays(1), 7500.0);

			client1.addAccount(account1);
			client1.addAccount(account2);
			accountRepository.save(account1);
			accountRepository.save(account2);

			Transaction transaction1, transaction2 ;
			transaction1 = new Transaction(TransactionType.Debit, -5000.00, "Debito", LocalDateTime.now());
			transaction2 = new Transaction(TransactionType.Credit, 7000.00, "Credito", LocalDateTime.now());
			account1.addTransaction(transaction1);
			account2.addTransaction(transaction2);
			transactionRepository.save(transaction1);
			transactionRepository.save(transaction2);

			Loan loan1 = new Loan("Hipotecario", 500000L, List.of(12,24,36,48,60));
			Loan loan2 = new Loan("Personal", 100000L, List.of( 6,12,24));
			Loan loan3 = new Loan("Automotriz", 300000L, List.of( 6,12,24,36));
			loanRepository.save(loan1);
			loanRepository.save(loan2);
			loanRepository.save(loan3);

			ClientLoan clientLoan1 = new ClientLoan(400000L,60); //
			ClientLoan clientLoan2 = new ClientLoan(200000L,36); // client1, loan2
			ClientLoan clientLoan3 = new ClientLoan(100000L,24); // client2, loan2
			ClientLoan clientLoan4 = new ClientLoan(200000L,36); // client2, loan3

			client1.addClientLoan(clientLoan1);
			loan1.addClientLoan(clientLoan1);
			client1.addClientLoan(clientLoan2);
			loan2.addClientLoan(clientLoan2);
			client2.addClientLoan(clientLoan3);
			loan2.addClientLoan(clientLoan3);
			client2.addClientLoan(clientLoan4);
			loan3.addClientLoan(clientLoan4);
			clientLoanRepository.save(clientLoan1);
			clientLoanRepository.save(clientLoan2);
			clientLoanRepository.save(clientLoan3);
			clientLoanRepository.save(clientLoan4);

			Card card1= new Card();
			card1.setCardHolder((client1.getFirstName() + " " + client1.getLastName()).toUpperCase());
			card1.setNumber("3325-6745-7876-4445");
			card1.setColor(CardColor.GOLD);
			card1.setType(CardType.DEBIT);
			card1.setCvv((short) 990);
			card1.setFromDate(LocalDate.now());
			card1.setThruDate(LocalDate.now().plusYears(5));
			client1.addCard(card1);

			Card card2= new Card();
			card2.setCardHolder((client1.getFirstName() + " " + client1.getLastName()).toUpperCase());
			card2.setType(CardType.CREDIT);
			card2.setColor(CardColor.TITANIUM);
			card2.setNumber("2234-6745-552-7888");
			card2.setCvv((short) 750);
			card2.setFromDate(LocalDate.now());
			card2.setThruDate(LocalDate.now().plusYears(5));
			client1.addCard(card2);

			cardRepository.save(card1);
			cardRepository.save(card2);
		};
	}
}
