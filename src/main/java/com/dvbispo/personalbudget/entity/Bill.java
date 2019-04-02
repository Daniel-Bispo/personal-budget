package com.dvbispo.personalbudget.entity;

import com.dvbispo.personalbudget.entity.enums.BillType;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Document(collection = "bill")
public class Bill implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;
    private String name;
    private int dayOfMonth;
    private Double value;
    private BillType billType;

    private List<String> notes = new ArrayList<>();

    public Bill() {
    }

    public Bill(String id, String name, int dayOfMonth, Double value, BillType billType) {
        this.id = id;
        this.name = name;
        this.dayOfMonth = dayOfMonth;
        this.value = value;
        this.billType = billType;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDate() {
        return dayOfMonth;
    }

    public void setDate(int dayOfMonth) {
        this.dayOfMonth = dayOfMonth;
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Bill)) return false;
        Bill bill = (Bill) o;
        return getId() == bill.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}