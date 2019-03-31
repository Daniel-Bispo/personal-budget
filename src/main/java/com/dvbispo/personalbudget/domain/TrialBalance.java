package com.dvbispo.personalbudget.domain;

import com.dvbispo.personalbudget.domain.enums.BillType;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Document(value = "trialbalances")
public class TrialBalance {

    @Id
    private int id;
    private int month;

    @DBRef(lazy = true)
    private List<Bill> bills = new ArrayList<>();

    @DBRef(lazy = true)
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TrialBalance)) return false;
        TrialBalance that = (TrialBalance) o;
        return getId() == that.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
