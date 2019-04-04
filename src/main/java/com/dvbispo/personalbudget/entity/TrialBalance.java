package com.dvbispo.personalbudget.entity;

import com.dvbispo.personalbudget.entity.enums.BillType;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.math.MathContext;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Document
public class TrialBalance {

    @Id
    private String id;
    private int year;
    private int month;

    @DBRef
    private List<Bill> bills = new ArrayList<>();

    private List<String> notes = new ArrayList<>();

    public TrialBalance() {
    }

    public TrialBalance(String id, int year, int month) {
        this.id = id;
        this.year = year;
        this.month = month;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public YearMonth getYearMonth() {
        return YearMonth.of(year,month);
    }

    public List<Bill> getBills() {
        return bills;
    }

    public void addBill(Bill bill) {
        this.bills.add(bill);
    }

    public List<String> getNotes() {
        return notes;
    }

    public void addNote(String note) {
        this.notes.add(note);
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
