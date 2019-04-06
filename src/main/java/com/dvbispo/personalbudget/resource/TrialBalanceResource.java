package com.dvbispo.personalbudget.resource;

import com.dvbispo.personalbudget.domain.TrialBalance;
import com.dvbispo.personalbudget.dto.TrialBalanceDTO;
import com.dvbispo.personalbudget.service.TrialBalanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/trialbalances")
public class TrialBalanceResource {

    @Autowired
    private TrialBalanceService service;

    @GetMapping
    public ResponseEntity<List<TrialBalance>> findAll(){

        List<TrialBalance> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<TrialBalanceDTO> findById(@PathVariable String id){

        TrialBalance trialBalance = service.findById(id);

        return ResponseEntity.ok().body(new TrialBalanceDTO(trialBalance));
    }
}
