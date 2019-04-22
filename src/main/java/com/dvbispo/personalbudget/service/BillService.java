package com.dvbispo.personalbudget.service;

import com.dvbispo.personalbudget.domain.Bill;
import com.dvbispo.personalbudget.domain.TrialBalance;
import com.dvbispo.personalbudget.dto.BillDTO;
import com.dvbispo.personalbudget.repository.BillRepository;
import com.dvbispo.personalbudget.repository.TrialBalanceRepository;
import com.dvbispo.personalbudget.service.exception.NullObject;
import com.dvbispo.personalbudget.service.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class BillService {

    @Autowired
    private BillRepository billRepository;
    @Autowired
    private TrialBalanceRepository trialBalanceRepository;

    public List<Bill> findAll(){
        return billRepository.findAll();
    }

    public Bill findById(String id){

        Optional<Bill> bill = billRepository.findById(id);

        if(bill.isEmpty()){
            throw new ObjectNotFoundException("Bill not found!");
        }

        return bill.get();
    }

    public Bill insert(Bill bill){

        if(bill == null){
            throw new NullObject("Null Bill insertion!");
        }

        bill.setStatus();
        Bill newBill = billRepository.insert(bill);

        /* resort the new Trial Balance */
        TrialBalance trialBalance = trialBalanceRepository.findById(newBill.getTrialBalanceId()).get();
        trialBalance.getBills().sort((x,y) -> Integer.compare(x.getDueDay(), y.getDueDay()));
        trialBalance.addBill(newBill);
        trialBalanceRepository.save(trialBalance);

        return newBill;
    }

    public Bill update(Bill newBill){

        Bill oldBill = billRepository.findById(newBill.getId()).get();
        updateData(newBill, oldBill);
        return billRepository.save(oldBill);
    }

    public void updateAll(List<Bill> billList){
        billList.forEach(
                x -> {
                    Bill oldBill = billRepository.findById(x.getId()).get();
                    updateData(x, oldBill);
                    billRepository.save(oldBill);
                }
        );
    }

    private void updateData(Bill newBill, Bill oldBill){

        /* keep the new trial balance's year and month */
        TrialBalance trialBalance = trialBalanceRepository.findById(newBill.getTrialBalanceId()).get();

        oldBill.setName(newBill.getName());
        oldBill.setDueYear(trialBalance.getYear());
        oldBill.setDueMonth(trialBalance.getMonth());
        oldBill.setDueDay(newBill.getDueDay());
        oldBill.setValue(newBill.getValue());
        oldBill.setBillType(newBill.getBillType());

        /* change the bill's information into the Trial Balance */
        changeTrialBalance(newBill, oldBill);

        /* change its Trial Balance */
        oldBill.setTrialBalanceId(newBill.getTrialBalanceId());

        /* check if it is payed and update the status */
        oldBill.setPayed(newBill.getPayed());
        oldBill.setStatus();

        oldBill.setNotes(newBill.getNote());
    }

    private void changeTrialBalance(Bill newBill, Bill oldBill){

        TrialBalance newTrialBalance = trialBalanceRepository.findById(newBill.getTrialBalanceId()).get();
        TrialBalance oldTrialBalance = trialBalanceRepository.findById(oldBill.getTrialBalanceId()).get();

        /* remove the old bill from its old Trial Balance */
        oldTrialBalance.getBills().removeIf(x -> x.getId().equals(oldBill.getId()));

        /* insert the old bill into the its new Trial Balance */
        newTrialBalance.addBill(oldBill);

        /* resort the new Trial Balance */
        newTrialBalance.getBills().sort((x,y) -> Integer.compare(x.getDueDay(), y.getDueDay()));

        /* update both Trial Balances*/
        trialBalanceRepository.saveAll(Arrays.asList(oldTrialBalance,newTrialBalance));
    }

    public void delete(String id){

        /* Check if the bill exist */
        findById(id);
        billRepository.deleteById(id);
    }

    public Bill fromDTO(BillDTO billDTO){
        Bill bill = new Bill(
                billDTO.getId(),
                billDTO.getName(),
                billDTO.getDueYear(),
                billDTO.getDueMonth(),
                billDTO.getDueDay(),
                billDTO.getValue(),
                billDTO.getBillType(),
                billDTO.getTrialBalanceId(),
                billDTO.getNote()
        );

        if(billDTO.getStatus() == null){
            return bill;
        }

        if(billDTO.getStatus().equals("Payed")){
            bill.setPayed(true);
        }
        else{
            bill.setPayed(false);
        }

        return bill;
    }

    public void setAllYearAndMonth(TrialBalance trialBalance){
        trialBalance.getBills().forEach(
                x -> {
                    x.setDueYear(trialBalance.getYear());
                    x.setDueMonth(trialBalance.getMonth());
                    billRepository.save(x);
                });
    }
}