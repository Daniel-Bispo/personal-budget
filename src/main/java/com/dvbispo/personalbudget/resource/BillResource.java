package com.dvbispo.personalbudget.resource;

import com.dvbispo.personalbudget.domain.Bill;
import com.dvbispo.personalbudget.domain.TrialBalance;
import com.dvbispo.personalbudget.dto.BillDTO;
import com.dvbispo.personalbudget.service.BillService;
import com.dvbispo.personalbudget.service.TrialBalanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/bills")
public class BillResource {

    @Autowired
    private BillService billService;
    @Autowired
    private TrialBalanceService trialBalanceService;

    @GetMapping
    public ResponseEntity<List<Bill>> findAll(){
        List<Bill> list = billService.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<BillDTO> findById(@PathVariable String id){
        Bill bill = billService.findById(id);
        return ResponseEntity.ok().body(new BillDTO(bill));
    }

    @PostMapping(value = "/{trialBalanceId}")
    public ResponseEntity<Void> insert(@RequestBody Bill bill, @PathVariable String trialBalanceId){

        //TODO: use fromDTO to insert a new bill;

        Bill newBill = billService.insert(bill);

        TrialBalance trialBalance = trialBalanceService.findById(trialBalanceId);

        /* Add the new bill to the list */
        trialBalance.addBill(newBill);

        /* Update account values */
        trialBalance.setTotalCredit();
        trialBalance.setTotalDebt();
        trialBalance.setBalance();

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newBill.getId())
                .toUri();
        return ResponseEntity.created(uri).build();

        //TODO: create the upDate(Bill newBill) method
        //TODO: create the delete(String id) method
    }


}
