package com.apmindhub.homeBankingmh;

import com.apmindhub.homeBankingmh.models.Account;
import com.apmindhub.homeBankingmh.models.Client;
import com.apmindhub.homeBankingmh.repository.AccountRepository;
import com.apmindhub.homeBankingmh.repository.ClientRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;

@SpringBootApplication
public class HomeBankingmhApplication {

	public static void main(String[] args) {
		SpringApplication.run(HomeBankingmhApplication.class, args);
	}
	@Bean
	public CommandLineRunner init(ClientRepository clientRepository, AccountRepository accountRepository){
		return args -> {
			Client client1 = new Client("Melba", "Morel", "melba@mindhub.com" );
			Client client2 = new Client("Jack", "Bauer", "jackbauer@gmail.com");
			Client client3 = new Client("Chloe", "O'Brian", "cloeobrian@hotmail.com");
			clientRepository.save(client1);
			clientRepository.save(client2);
			clientRepository.save(client3);

			Account account1 = new Account(client1, "VIN001", LocalDate.now(), 5000.0);
			Account account2 = new Account( client2,"VIN002", LocalDate.now().plusDays(1), 7500.0);

			accountRepository.save(account1);
			accountRepository.save(account2);
		};
	}
}
