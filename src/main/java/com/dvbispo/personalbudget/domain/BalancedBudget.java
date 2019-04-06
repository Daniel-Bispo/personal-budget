package com.dvbispo.personalbudget.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Document
public class BalancedBudget {

    @Id
    private String id;
    @NotNull
    private Integer year;

    @DBRef
    private List<TrialBalance> trialBalances = new ArrayList<>();

    private List<String> notes = new ArrayList<>();

    public BalancedBudget(){
    }

    public BalancedBudget(String id, Integer year) {
        this.id = id;
        this.year = year;
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
