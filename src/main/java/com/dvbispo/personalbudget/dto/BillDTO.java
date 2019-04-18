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
    private String trialBalanceId;
    private String notes;

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
        this.trialBalanceId = bill.getTrialBalanceId();
        this.notes = bill.getNotes();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getDueYear() {
        return dueYear;
    }

    public Integer getDueMonth() {
        return dueMonth;
    }

    public Integer getDueDay() {
        return dueDay;
    }

    public Double getValue() {
        return value;
    }

    public BillType getBillType() {
        return billType;
    }

    public String getStatus() {
        return status;
    }

    public String getTrialBalanceId() {
        return trialBalanceId;
    }

    public String getNotes() {
        return notes;
    }
}
