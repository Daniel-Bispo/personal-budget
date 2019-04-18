package com.dvbispo.personalbudget.domain;

import com.dvbispo.personalbudget.domain.enums.BillType;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@Document
public class Bill implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;
    @NotNull
    private String name;
    @NotNull
    private Integer dueYear;
    @NotNull
    private Integer dueMonth;
    @NotNull
    private Integer dueDay;
    @NotNull
    private Double value;
    @NotNull
    private BillType billType;
    @NotNull
    private String trialBalanceId;
    private Boolean payed;
    private String status;
    private String notes;

    public Bill() {
    }

    public Bill(String id, String name, Integer dueYear, Integer dueMonth, Integer dueDay, Double value,
                BillType billType, String trialBalanceId, String notes) {
        this.id = id;
        this.name = name;
        this.dueYear = dueYear;
        this.dueMonth = dueMonth;
        this.dueDay = dueDay;
        this.value = value;
        this.billType = billType;
        this.trialBalanceId = trialBalanceId;
        this.notes = notes;
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

    public Boolean getPayed() {
        return payed;
    }

    public void setPayed(Boolean payed) {
        this.payed = payed;
    }

    public void setStatus(){

        LocalDate dateToday = LocalDate.now();
        LocalDate dueDate = LocalDate.of(dueYear, dueMonth, dueDay);

        if(payed) {
            this.status = "Payed";
        }
        else if(!payed && dateToday.isAfter(dueDate) && billType.equals(BillType.CREDIT)){
            this.status = "Overdue";
        }
        else if(!payed && dateToday.isAfter(dueDate.minusDays(3)) && billType.equals(BillType.CREDIT)){
            this.status = "Closing";
        }
        else{
            this.status = "Unpaid";
        }
    }

    public String getStatus() {
        return status;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getTrialBalanceId() {
        return trialBalanceId;
    }

    public void setTrialBalanceId(String trialBalanceId) {
        this.trialBalanceId = trialBalanceId;
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
