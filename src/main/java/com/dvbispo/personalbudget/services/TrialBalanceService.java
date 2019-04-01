package com.dvbispo.personalbudget.services;


import com.dvbispo.personalbudget.domain.TrialBalance;
import com.dvbispo.personalbudget.repositories.TrialBalanceRepository;
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
}
