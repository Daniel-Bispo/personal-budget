package com.dvbispo.personalbudget.resources;

import com.dvbispo.personalbudget.domain.TrialBalance;
import com.dvbispo.personalbudget.services.TrialBalanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/tb")
public class TrialBalanceResource {

    @Autowired
    private TrialBalanceService service;

    @GetMapping("/trialbalances")
    public ResponseEntity<List<TrialBalance>> findAll(){

        List<TrialBalance> trialBalanceList = service.findAll();

        return ResponseEntity.ok().body(trialBalanceList);
    }
}
