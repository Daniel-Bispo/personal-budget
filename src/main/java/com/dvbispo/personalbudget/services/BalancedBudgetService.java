package com.dvbispo.personalbudget.services;

import com.dvbispo.personalbudget.domain.BalancedBudget;
import com.dvbispo.personalbudget.repositories.BalancedBudgetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BalancedBudgetService {

    @Autowired
    private BalancedBudgetRepository repository;

    public List<BalancedBudget> findAll(){
        return repository.findAll();
    }
}
