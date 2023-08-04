package com.apmindhub.homeBankingmh;

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
			Client client = new Client();
			clientRepository.save(client);
			clientRepository.save(new Client("Jack", "Bauer", "jackbauer@gmail.com"));
			clientRepository.save(new Client("Chloe", "O'Brian", "cloeobrian@hotmail.com"));

		};
	}
}
