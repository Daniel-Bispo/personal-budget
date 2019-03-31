package com.dvbispo.personalbudget.domain;

import com.dvbispo.personalbudget.domain.enums.BillType;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TrialBalance {
    private int id;
    private int month;
    private Double totalDebt = 0.0;
    private Double totalCredit = 0.0;
    private Double balance = 0.0;

    private List<String> notes = new ArrayList<>();;

    private List<Bill> bills = new ArrayList<>();;

    public TrialBalance() {
    }

    public TrialBalance(int id) {
        this.id = id;
        this.month = LocalDate.now().getMonthValue();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMonth() {
        return month;
    }

    public Double getTotalDebt() {
        return totalDebt;
    }

    private void sumTotalDebt() {

        totalDebt = bills.stream()
                .filter(x -> x.getBillType() == BillType.DEBT)
                .mapToDouble(x -> x.getValue())
                .sum();
    }

    public Double getTotalCredit() {
        return totalCredit;
    }

    private void sumTotalCredit() {
        totalCredit = bills.stream()
                .filter(x -> x.getBillType() == BillType.CREDIT)
                .mapToDouble(x -> x.getValue())
                .sum();
    }

    public Double getBalance() {
        return balance;
    }

    private void setBalance() {
        this.balance = totalDebt - totalCredit;
    }

    public List<String> getNotes() {
        return notes;
    }

    public void addNote(String note) {
        this.notes.add(note);
    }

    public List<Bill> getBills() {
        return bills;
    }

    public void addBill(Bill bill) {
        this.bills.add(bill);
        sumTotalDebt();
        sumTotalCredit();
        setBalance();
    }

    @Override
    public String toString() {
        return "TrialBalance{" +
                "id=" + id +
                ", month=" + month +
                ", totalDebt=" + totalDebt +
                ", totalCredit=" + totalCredit +
                ", balance=" + balance +
                ", notes=" + notes +
                ", bills=" + bills +
                '}';
    }
}
