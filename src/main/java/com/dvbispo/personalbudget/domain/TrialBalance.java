package com.dvbispo.personalbudget.domain;

import com.dvbispo.personalbudget.domain.enums.BillType;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Document(collection = "trialbalance")
public class TrialBalance {

    @Id
    private String id;
    private int month;

    @DBRef(lazy = true)
    private List<Bill> bills = new ArrayList<>();

    private List<String> notes = new ArrayList<>();

    public TrialBalance() {
    }

    public TrialBalance(String id, int month) {
        this.id = id;
        this.month = month;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

        BigDecimal cal = new BigDecimal(bills.stream()
                .filter(x -> x.getBillType() == BillType.DEBT)
                .mapToDouble(x -> x.getValue())
                .sum(),new MathContext(10));

        return cal.doubleValue();
    }

    public Double getTotalCredit() {

        BigDecimal cal = new BigDecimal(bills.stream()
                            .filter(x -> x.getBillType() == BillType.CREDIT)
                            .mapToDouble(x -> x.getValue())
                            .sum(),new MathContext(10));

        return cal.doubleValue();
    }


    public Double getBalance() {

        BigDecimal cal = new BigDecimal(getTotalDebt() - getTotalCredit(),
                                new MathContext(10));

        return cal.doubleValue();
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
