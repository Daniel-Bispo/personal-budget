package com.dvbispo.personalbudget.domain;

import com.dvbispo.personalbudget.domain.enums.BillType;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Bill {

    private int id;
    private String name;
    private Date date;
    private Double value;
    private BillType billType;
    private List<String> notes;

    public Bill() {
    }

    public Bill(int id, String name, Date date, Double value, BillType billType) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.value = value;
        this.billType = billType;
        this.notes = new ArrayList<>();
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
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

    public void setNotes(String note) {
        this.notes.add(note);
    }

    @Override
    public String toString() {
        return "Bill{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", date=" + date +
                ", value=" + value +
                ", billType=" + billType +
                ", notes=" + notes +
                '}';
    }
}
