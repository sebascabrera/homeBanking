package com.apmindhub.homeBankingmh;

import com.apmindhub.homeBankingmh.models.Account;
import com.apmindhub.homeBankingmh.models.Client;
import com.apmindhub.homeBankingmh.repository.ClientRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class HomeBankingmhApplication {

	public static void main(String[] args) {
		SpringApplication.run(HomeBankingmhApplication.class, args);
	}
	@Bean
	public CommandLineRunner init(ClientRepository clientRepository){
		return args -> {
			Client client1 = new Client("Melba", "Morel", "melba@mindhub.com" );
			Client client2 = new Client("Jack", "Bauer", "jackbauer@gmail.com");
			Client client3 = new Client("Chloe", "O'Brian", "cloeobrian@hotmail.com");
			clientRepository.save(client1);
			clientRepository.save(client2);
			clientRepository.save(client3);


		};
	}
}
