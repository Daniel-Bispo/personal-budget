package com.dvbispo.personalbudget.domain;

import com.dvbispo.personalbudget.domain.enums.BillType;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
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
    @NotNull
    private Integer year;
    @NotNull
    private Integer month;

    private Double totalDebt;
    private Double totalCredit;
    private Double balance;

    @DBRef
    private List<Bill> bills = new ArrayList<>();

    private List<String> notes = new ArrayList<>();

    public TrialBalance() {
    }

    public TrialBalance(String id, Integer year, Integer month) {
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

    public void setTotalDebt() {

        BigDecimal cal = new BigDecimal(bills.stream()
                .filter(x -> x.getBillType() == BillType.DEBT)
                .mapToDouble(x -> x.getValue())
                .sum(),new MathContext(10));

        this.totalDebt =  cal.doubleValue();
    }

    public Double getTotalCredit() {
        return totalCredit;
    }

    public void setTotalCredit() {

        BigDecimal cal = new BigDecimal(bills.stream()
                .filter(x -> x.getBillType() == BillType.CREDIT)
                .mapToDouble(x -> x.getValue())
                .sum(),new MathContext(10));

        this.totalCredit = cal.doubleValue();
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance() {

        BigDecimal cal = new BigDecimal(getTotalDebt() - getTotalCredit(),
                new MathContext(10));

        this.balance = cal.doubleValue();
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
