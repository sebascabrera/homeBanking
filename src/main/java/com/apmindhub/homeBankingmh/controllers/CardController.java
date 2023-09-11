package com.apmindhub.homeBankingmh.controllers;

import com.apmindhub.homeBankingmh.dtos.CardDTO;
import com.apmindhub.homeBankingmh.models.Card;
import com.apmindhub.homeBankingmh.models.CardColor;
import com.apmindhub.homeBankingmh.models.CardType;
import com.apmindhub.homeBankingmh.models.Client;
import com.apmindhub.homeBankingmh.repository.CardRepository;
import com.apmindhub.homeBankingmh.repository.ClientRepository;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import static com.apmindhub.homeBankingmh.utils.Util.getRandomNumber;
import static com.apmindhub.homeBankingmh.utils.Util.getRandomNumberCvv;

@RestController
@RequestMapping("/api")
public class CardController {
    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private CardRepository cardRepository;
    @RequestMapping("/cards")
    public List<CardDTO> getCard (){
        List<Card> cardList = cardRepository.findAll();
        List<CardDTO> cardDTOList = cardList.stream().map(card -> new CardDTO(card)).collect(Collectors.toList());
        return cardDTOList;
    }
    @RequestMapping("/cards/{id}")
    public CardDTO getCardDTO (@PathVariable Long id){
        return cardRepository.findById(id).map(card -> new CardDTO(card)).orElse(null);
    }

    @PostMapping("/clients/current/cards")
    public ResponseEntity<Object> createCard(Authentication authentication) {
        Client clientAuth = clientRepository.findByEmail(authentication.name());

        if (clientAuth.getCards().size() >=3) {
    return new ResponseEntity<>("You have reached the maximum number of cards.", HttpStatus.FORBIDDEN);
}

        String cardNumber;
        int cardCvv;
        do {
            cardNumber = "CARD" + getRandomNumber(10000000, 99999999);
            cardCvv = getRandomNumberCvv(1, 999);
        } while (cardRepository.existsByNumber(cardNumber));

        Card card = new Card(cardNumber, CardType.CREDIT, CardColor.GOLD, cardNumber, (short) cardCvv, LocalDate.now(), LocalDate.now().plusYears(5));
        clientAuth.addCard(card);
        cardRepository.save(card);

        return new ResponseEntity<>("New card created", HttpStatus.CREATED);
    }

}
   /* @RequestMapping("/accounts/{id}")
    public AccountDTO getAccountDTO(@PathVariable Long id){
        return accountRepository.findById(id)
                .map(account -> new AccountDTO(account))
                .orElse(null);*/

