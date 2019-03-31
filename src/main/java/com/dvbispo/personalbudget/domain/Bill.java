package com.dvbispo.personalbudget.domain;

import com.dvbispo.personalbudget.domain.enums.BillType;

import java.util.ArrayList;
import java.util.List;

public class Bill {

    private int id;
    private String name;
    private int day;
    private Double value;
    private BillType billType;
    private List<String> notes = new ArrayList<>();

    public Bill() {
    }

    public Bill(int id, String name, int day, Double value, BillType billType) {
        this.id = id;
        this.name = name;
        this.day = day;
        this.value = value;
        this.billType = billType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDate() {
        return day;
    }

    public void setDate(int day) {
        this.day = day;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public BillType getBillType() {
        return billType;
    }

    public void setBillType(BillType billType) {
        this.billType = billType;
    }

    public List<String> getNotes() {
        return notes;
    }

    public void addNotes(String note) {
        this.notes.add(note);
    }

    @Override
    public String toString() {
        return "Bill{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", date=" + day +
                ", value=" + value +
                ", billType=" + billType +
                ", notes=" + notes +
                '}';
    }
}
