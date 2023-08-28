package com.apmindhub.homeBankingmh.controllers;

import com.apmindhub.homeBankingmh.dtos.CardDTO;
import com.apmindhub.homeBankingmh.models.Card;
import com.apmindhub.homeBankingmh.repository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class CardController {
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
}
   /* @RequestMapping("/accounts/{id}")
    public AccountDTO getAccountDTO(@PathVariable Long id){
        return accountRepository.findById(id)
                .map(account -> new AccountDTO(account))
                .orElse(null);*/

