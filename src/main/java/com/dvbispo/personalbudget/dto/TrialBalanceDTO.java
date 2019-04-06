package com.dvbispo.personalbudget.dto;

import com.dvbispo.personalbudget.domain.TrialBalance;

import java.io.Serializable;

public class TrialBalanceDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;
    private Integer year;
    private Integer month;
    private Double totalDebt;
    private Double totalCredit;
    private Double balance;

    public TrialBalanceDTO() {
    }

    public TrialBalanceDTO(TrialBalance trialBalance) {
        this.id = trialBalance.getId();
        this.year = trialBalance.getYear();
        this.month = trialBalance.getMonth();
        this.totalDebt = trialBalance.getTotalDebt();
        this.totalCredit = trialBalance.getTotalCredit();
        this.balance = trialBalance.getBalance();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public Double getTotalDebt() {
        return totalDebt;
    }

    public void setTotalDebt(Double totalDebt) {
        this.totalDebt = totalDebt;
    }

    public Double getTotalCredit() {
        return totalCredit;
    }

    public void setTotalCredit(Double totalCredit) {
        this.totalCredit = totalCredit;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }
}