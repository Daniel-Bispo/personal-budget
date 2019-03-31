package com.dvbispo.personalbudget.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Document(collection = "balancedbudgets")
public class BalancedBudget {

    @Id
    private int id;
    private int year;

    @DBRef(lazy = true)
    private List<TrialBalance> trialBalances = new ArrayList<>();

    @DBRef(lazy = true)
    private List<String> notes = new ArrayList<>();

    public BalancedBudget(){
    }

    public BalancedBudget(int id, int year) {
        this.id = id;
        this.year = year;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public void addTrialBalances(TrialBalance trialBalances) {
        this.trialBalances.add(trialBalances);
    }

    public List<String> getNotes() {
        return notes;
    }

    public void addNotes(String notes) {
        this.notes.add(notes);
    }

    private void addTrialBalance(TrialBalance trialBalance){
            trialBalances.add(trialBalance);
    }

    public Double getBalance(){
        return trialBalances.stream().mapToDouble(x -> x.getBalance()).sum();
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
