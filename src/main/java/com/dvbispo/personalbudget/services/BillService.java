package com.dvbispo.personalbudget.services;

import com.dvbispo.personalbudget.domain.Bill;
import com.dvbispo.personalbudget.repositories.BillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BillService {

    @Autowired
    BillRepository repository;

    public List<Bill> findAll(){
        return repository.findAll();
    }
}
