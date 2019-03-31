package com.dvbispo.personalbudget.domain;

import java.util.ArrayList;
import java.util.List;

public class BalancedBudget {

    private int id;
    private int year;
    private List<TrialBalance> trialBalances = new ArrayList<>();
    private List<String> notes = new ArrayList<>();

    public BalancedBudget(){
        addTrialBalances();
    }

    public BalancedBudget(int id, int year) {
        this.id = id;
        this.year = year;
        addTrialBalances();
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

    private void addTrialBalances(){
        for(int i =1; i <= 12; i++){
            trialBalances.add(new TrialBalance(i));
        }
    }

    public Double getcBalance(){
        return trialBalances.stream().mapToDouble(x -> x.getBalance()).sum();
    }
}
