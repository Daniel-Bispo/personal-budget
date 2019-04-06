package com.dvbispo.personalbudget.dto;

import com.dvbispo.personalbudget.domain.Bill;
import com.dvbispo.personalbudget.domain.enums.BillType;

import java.io.Serializable;

public class BillDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;
    private String name;
    private Integer dueYear;
    private Integer dueMonth;
    private Integer dueDay;
    private Double value;
    private BillType billType;
    private String status;

    public BillDTO() {
    }

    public BillDTO(Bill bill) {
        this.id = bill.getId();
        this.name = bill.getName();
        this.dueYear = bill.getDueYear();
        this.dueMonth = bill.getDueMonth();
        this.dueDay = bill.getDueDay();
        this.value = bill.getValue();
        this.billType = bill.getBillType();
        this.status = bill.getStatus();
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

    public Integer getDueYear() {
        return dueYear;
    }

    public void setDueYear(Integer dueYear) {
        this.dueYear = dueYear;
    }

    public Integer getDueMonth() {
        return dueMonth;
    }

    public void setDueMonth(Integer dueMonth) {
        this.dueMonth = dueMonth;
    }

    public Integer getDueDay() {
        return dueDay;
    }

    public void setDueDay(Integer dueDay) {
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
