package com.dvbispo.personalbudget.domain;

import java.util.ArrayList;
import java.util.List;

public class BalancedBudget {

    private int id;
    private int year;
    private List<TrialBalance> trialBalances = new ArrayList<>();
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
    public String toString() {
        return "BalancedBudget{" +
                "id=" + id +
                ", year=" + year +
                ", balance=" + getBalance() +
                ", notes=" + notes +
                '}';
    }
}
