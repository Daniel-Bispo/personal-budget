package com.dvbispo.personalbudget.service;

import com.dvbispo.personalbudget.domain.TrialBalance;
import com.dvbispo.personalbudget.repository.TrialBalanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrialBalanceService {

    @Autowired
    private TrialBalanceRepository repository;

    public List<TrialBalance> findAll(){
        return repository.findAll();
    }

    public TrialBalance findById(String id){

        TrialBalance trialBalance = repository.findById(id).get();

        //TODO: Check if trialBalance is not null

        return trialBalance;
    }



}
