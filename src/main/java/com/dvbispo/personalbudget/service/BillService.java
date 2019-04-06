package com.dvbispo.personalbudget.service;

import com.dvbispo.personalbudget.domain.Bill;
import com.dvbispo.personalbudget.dto.BillDTO;
import com.dvbispo.personalbudget.repository.BillRepository;
import com.dvbispo.personalbudget.service.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BillService {

    @Autowired
    private BillRepository repository;

    public List<Bill> findAll(){
        return repository.findAll();
    }

    public Bill findById(String id){

        Bill bill = repository.findById(id).get();

        if(bill == null){
            throw new ObjectNotFoundException("Bill not found.");
        }
        return bill;
    }

    public Bill insert(Bill bill){
        return repository.insert(bill);
    }

    public Bill update(Bill newBill){

        Bill oldBill = repository.findById(newBill.getId()).get();
        upDateData(newBill, oldBill);
        return repository.save(oldBill);
    }

    private void upDateData(Bill newBill, Bill oldBill){
        oldBill.setName(newBill.getName());
        oldBill.setDueYear(newBill.getDueYear());
        oldBill.setDueMonth(newBill.getDueMonth());
        oldBill.setDueDay(newBill.getDueDay());
        oldBill.setValue(newBill.getValue());
        oldBill.setBillType(newBill.getBillType());
        oldBill.setPayed(newBill.getPayed());
        oldBill.setStatus();
    }

    public void delete(String id){

        /* Check if the bill exist */
        findById(id);
        repository.deleteById(id);
    }

    public Bill fromDTO(BillDTO billDTO){
        return new Bill(
                billDTO.getId(),
                billDTO.getName(),
                billDTO.getDueYear(),
                billDTO.getDueMonth(),
                billDTO.getDueDay(),
                billDTO.getValue(),
                billDTO.getBillType()
        );
    }




}
