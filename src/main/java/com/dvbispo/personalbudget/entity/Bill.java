package com.dvbispo.personalbudget.entity;

import com.dvbispo.personalbudget.entity.enums.BillType;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Document
public class Bill implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;
    private String name;
    private int dueYear;
    private int dueMonth;
    private int dueDay;
    private Double value;
    private BillType billType;
    private Boolean payed;

    private List<String> notes = new ArrayList<>();

    public Bill() {
    }

    public Bill(String id, String name, int dueYear, int dueMonth, int dueDay, Double value, BillType billType) {
        this.id = id;
        this.name = name;
        this.dueYear = dueYear;
        this.dueMonth = dueMonth;
        this.dueDay = dueDay;
        this.value = value;
        this.billType = billType;
        this.payed = false;
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

    public LocalDate getDate() {
        return  LocalDate.of(dueYear, dueMonth, dueDay);
    }

    public void setDueYear(int dueYear) {
        this.dueYear = dueYear;
    }

    public void setDueMonth(int dueMonth) {
        this.dueMonth = dueMonth;
    }

    public void setDueDay(int dueDay) {
        this.dueDay = dueDay;
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

    public void setPayed(Boolean payed) {
        this.payed = payed;
    }

    public String getStatus(){

        LocalDate dateToday = LocalDate.now();
        LocalDate dueDate = getDate();

        if(payed) {
            return "Payed";
        }
        else if(!payed && dateToday.isAfter(dueDate)){
            return "Overdue";
        }
        else if(!payed && dateToday.isAfter(dueDate.minusDays(3))){
            return "Closing";
        }
        else{
            return "Unpaid";
        }
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
