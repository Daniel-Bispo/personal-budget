package com.dvbispo.personalbudget.service;

import com.dvbispo.personalbudget.domain.Bill;
import com.dvbispo.personalbudget.dto.BillDTO;
import com.dvbispo.personalbudget.repository.BillRepository;
import com.dvbispo.personalbudget.repository.TrialBalanceRepository;
import com.dvbispo.personalbudget.service.exception.NullObject;
import com.dvbispo.personalbudget.service.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        return billRepository.insert(bill);
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
        oldBill.setName(newBill.getName());
        oldBill.setDueYear(newBill.getDueYear());
        oldBill.setDueMonth(newBill.getDueMonth());
        oldBill.setDueDay(newBill.getDueDay());
        oldBill.setValue(newBill.getValue());
        oldBill.setBillType(newBill.getBillType());

        // TODO: check if it is necessary to change the information into the Trial Balance
        oldBill.setTrialBalanceId(newBill.getTrialBalanceId());

        oldBill.setPayed(newBill.getPayed());
        oldBill.setNotes(newBill.getNote());
        oldBill.setStatus(); // update the status
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
}