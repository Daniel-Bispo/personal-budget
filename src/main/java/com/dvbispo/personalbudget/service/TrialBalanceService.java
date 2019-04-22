package com.dvbispo.personalbudget.service;

import com.dvbispo.personalbudget.domain.TrialBalance;
import com.dvbispo.personalbudget.dto.TrialBalanceDTO;
import com.dvbispo.personalbudget.repository.TrialBalanceRepository;
import com.dvbispo.personalbudget.service.exception.NullObject;
import com.dvbispo.personalbudget.service.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TrialBalanceService {

    @Autowired
    private TrialBalanceRepository repository;
    @Autowired
    private BillService billService;

    public List<TrialBalance> findAll(){
        return repository.findAll();
    }

    public TrialBalance findById(String id){

        Optional<TrialBalance> trialBalance = repository.findById(id);

        if(trialBalance.isEmpty()){
            throw new ObjectNotFoundException("Trial Balance not found!");
        }

        return trialBalance.get();
    }

    public TrialBalance insert(TrialBalance trialBalance){

        if(trialBalance == null){
            throw new NullObject("Null Trial Balance insertion!");
        }

        return repository.insert(trialBalance);
    }

    public TrialBalance update(TrialBalance newTrialBalance){
        TrialBalance oldTrialBalance = findById(newTrialBalance.getId());
        updateData(newTrialBalance, oldTrialBalance);
        updateValues(oldTrialBalance);
        return repository.save(oldTrialBalance);
    }

    private void updateData(TrialBalance newTrialBalance, TrialBalance oldTrialBalance) {
        oldTrialBalance.setYear(newTrialBalance.getYear());
        oldTrialBalance.setMonth(newTrialBalance.getMonth());
        oldTrialBalance.setNote(newTrialBalance.getNote());

        /* update all bill's year and month*/
        oldTrialBalance.getBills().forEach(
                x -> {x.setDueYear(oldTrialBalance.getYear());
                x.setDueMonth(oldTrialBalance.getMonth());}
        );

        billService.updateAll(oldTrialBalance.getBills());
    }

    public void delete(String id){

        /* check if the Trial Balance exist*/
        findById(id);
        repository.deleteById(id);
    }

    public TrialBalance fromDTO(TrialBalanceDTO trialBalanceDTO){

        TrialBalance trialBalance = new TrialBalance(
                trialBalanceDTO.getId(),
                trialBalanceDTO.getYear(),
                trialBalanceDTO.getMonth(),
                trialBalanceDTO.getNote()
        );

        trialBalanceDTO.getBills().forEach(x -> trialBalance.addBill(x));

        updateValues(trialBalance);

        return trialBalance;
    }

    private void updateValues(TrialBalance trialBalance){
        trialBalance.setTotalDebt();
        trialBalance.setTotalCredit();
        trialBalance.setBalance();
    }
}
