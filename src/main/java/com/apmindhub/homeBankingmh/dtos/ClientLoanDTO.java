package com.apmindhub.homeBankingmh.dtos;

import com.apmindhub.homeBankingmh.models.ClientLoan;

import java.util.List;
import java.util.Set;

public class ClientLoanDTO {
    private Long id;

    private Long amount;

    private List<Integer> payments;

    public ClientLoanDTO() {
    }
public ClientLoanDTO(ClientLoan clientLoan){
        id = clientLoan.getId();
        amount = clientLoan.getAmount();
        payments = clientLoan.getLoan().getPayments();

}

    public Long getId() {
        return id;
    }

    public Long getAmount() {
        return amount;
    }

    public List<Integer> getPayments() {
        return payments;
    }
}
