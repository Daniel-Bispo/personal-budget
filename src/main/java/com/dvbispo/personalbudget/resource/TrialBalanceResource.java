package com.dvbispo.personalbudget.resource;

import com.dvbispo.personalbudget.domain.TrialBalance;
import com.dvbispo.personalbudget.dto.TrialBalanceDTO;
import com.dvbispo.personalbudget.service.BillService;
import com.dvbispo.personalbudget.service.TrialBalanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/trialbalances")
public class TrialBalanceResource {

    @Autowired
    private TrialBalanceService service;
    @Autowired
    private BillService billService;

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

    @PostMapping
    public ResponseEntity<TrialBalance> insert(@RequestBody TrialBalance trialBalance){

        /* Make sure all values are update */
        trialBalance.setTotalDebt();
        trialBalance.setTotalCredit();
        trialBalance.setBalance();

        TrialBalance newTrialBalance = service.insert(trialBalance);

        return ResponseEntity.ok().body(newTrialBalance);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<TrialBalanceDTO> update(@RequestBody TrialBalance trialBalance, @PathVariable String id){

        /* check if the bill exists */
        service.findById(id);

        trialBalance.setId(id);

        trialBalance = service.update(trialBalance);

        /* change all bills's year and month if necessary */
        billService.setAllYearAndMonth(trialBalance);

        return ResponseEntity.ok().body(new TrialBalanceDTO(trialBalance));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id){

        /* check if the bill exists */
        TrialBalance trialBalance = service.findById(id);

        /* delete all its bills */
        trialBalance.getBills().forEach(x -> billService.delete(x.getId()));

        service.delete(id);

        return ResponseEntity.noContent().build();
    }
}
