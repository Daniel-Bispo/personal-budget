package com.dvbispo.personalbudget.dto;

import com.dvbispo.personalbudget.domain.Bill;
import com.dvbispo.personalbudget.domain.TrialBalance;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class TrialBalanceDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;
    private Integer year;
    private Integer month;
    private Double totalDebt;
    private Double totalCredit;
    private Double balance;
    private List<Bill> bills = new ArrayList<>();
    private String note;

    public TrialBalanceDTO() {
    }

    public TrialBalanceDTO(TrialBalance trialBalance) {
        this.id = trialBalance.getId();
        this.year = trialBalance.getYear();
        this.month = trialBalance.getMonth();
        this.totalDebt = trialBalance.getTotalDebt();
        this.totalCredit = trialBalance.getTotalCredit();
        this.balance = trialBalance.getBalance();
        this.bills = trialBalance.getBills();
        this.note = trialBalance.getNote();
    }

    public String getId() {
        return id;
    }

    public Integer getYear() {
        return year;
    }

    public Integer getMonth() {
        return month;
    }

    public Double getTotalDebt() {
        return totalDebt;
    }

    public Double getTotalCredit() {
        return totalCredit;
    }

    public Double getBalance() {
        return balance;
    }

    public List<Bill> getBills() {
        return bills;
    }

    public String getNote() {
        return note;
    }
}