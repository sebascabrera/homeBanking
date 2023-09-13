package com.apmindhub.homeBankingmh.dtos;

import com.apmindhub.homeBankingmh.models.Loan;

import java.util.ArrayList;
import java.util.List;

public class LoanDTO {

    private long id;
    private String name;
    private Long maxAmount;
    private List<Integer> payments = new ArrayList<>();

    public LoanDTO() {
    }

    public LoanDTO(Loan loan) {
        id = loan.getId();
        name = loan.getName();
        maxAmount = loan.getMaxAmount();
        payments = loan.getPayments();
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Long getMaxAmount() {
        return maxAmount;
    }

    public List<Integer> getPayments() {
        return payments;
    }
}
