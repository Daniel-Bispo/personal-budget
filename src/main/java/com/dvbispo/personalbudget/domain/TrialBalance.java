package com.dvbispo.personalbudget.domain;

import com.dvbispo.personalbudget.domain.enums.BillType;

import java.util.ArrayList;
import java.util.List;

public class TrialBalance {
    private int id;
    private int month;

    private List<Bill> bills = new ArrayList<>();

    private List<String> notes = new ArrayList<>();

    public TrialBalance() {
    }

    public TrialBalance(int id, int month) {
        this.id = id;
        this.month = month;
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
    }

    public Double getTotalDebt() {
        return bills.stream()
                .filter(x -> x.getBillType() == BillType.DEBT)
                .mapToDouble(x -> x.getValue())
                .sum();
    }

    public Double getTotalCredit() {
        return bills.stream()
                .filter(x -> x.getBillType() == BillType.CREDIT)
                .mapToDouble(x -> x.getValue())
                .sum();
    }

    public Double getBalance() {
        return getTotalCredit() - getTotalDebt();
    }

    @Override
    public String toString() {
        return "TrialBalance{" +
                "id=" + id +
                ", month=" + month +
                ", balance=" + getBalance() +
                ", bills=" + bills +
                ", notes=" + notes +
                '}';
    }
}
