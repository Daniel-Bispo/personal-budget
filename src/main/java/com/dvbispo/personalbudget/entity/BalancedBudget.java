package com.dvbispo.personalbudget.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Document(collection = "balancedbudget")
public class BalancedBudget {

    @Id
    private String id;
    private int year;

    @DBRef(lazy = true)
    private List<TrialBalance> trialBalances = new ArrayList<>();

    private List<String> notes = new ArrayList<>();

    public BalancedBudget(){
    }

    public BalancedBudget(String id, int year) {
        this.id = id;
        this.year = year;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public List<TrialBalance> getTrialBalances() {
        return trialBalances;
    }

    public void addTrialBalance(TrialBalance trialBalance) {
        this.trialBalances.add(trialBalance);
    }

    public List<String> getNotes() {
        return notes;
    }

    public void addNotes(String notes) {
        this.notes.add(notes);
    }

    public Double getBalance(){

        BigDecimal calc = new BigDecimal(trialBalances.stream().mapToDouble(x -> x.getBalance()).sum(),
                            new MathContext(10));
        return calc.doubleValue();
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BalancedBudget)) return false;
        BalancedBudget that = (BalancedBudget) o;
        return getId() == that.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
