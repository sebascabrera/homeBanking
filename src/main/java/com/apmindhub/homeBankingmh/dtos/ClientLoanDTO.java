package com.apmindhub.homeBankingmh.dtos;

import com.apmindhub.homeBankingmh.models.ClientLoan;

import java.util.List;
import java.util.Set;

public class ClientLoanDTO {
    private Long id;

    private String name;

    private Long amount;

    private Integer payments;

    private Long loanId;

    public ClientLoanDTO() {
    }
public ClientLoanDTO(ClientLoan clientLoan){
        id = clientLoan.getId();
        name = clientLoan.getLoan().getName();
        loanId = clientLoan.getLoan().getId();
        amount = clientLoan.getAmount();
        payments = clientLoan.getPayments();

}

    public String getName() {
        return name;
    }

    public Long getLoanId() {
        return loanId;
    }

    public Long getId() {
        return id;
    }

    public Long getAmount() {
        return amount;
    }

    public Integer getPayments() {
        return payments;
    }
}
