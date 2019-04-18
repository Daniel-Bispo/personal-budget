package com.dvbispo.personalbudget.resource;

import com.dvbispo.personalbudget.domain.Bill;
import com.dvbispo.personalbudget.domain.TrialBalance;
import com.dvbispo.personalbudget.dto.BillDTO;
import com.dvbispo.personalbudget.repository.TrialBalanceRepository;
import com.dvbispo.personalbudget.service.BillService;
import com.dvbispo.personalbudget.service.TrialBalanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/bills")
public class BillResource {

    @Autowired
    private BillService billService;
    @Autowired
    private TrialBalanceService trialBalanceService;

    @GetMapping
    public ResponseEntity<List<BillDTO>> findAll(){
        List<Bill> list = billService.findAll();

        List<BillDTO> billDTOList = list.stream().map(x -> new BillDTO(x)).collect(Collectors.toList());

        return ResponseEntity.ok().body(billDTOList);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<BillDTO> findById(@PathVariable String id){
        Bill bill = billService.findById(id);
        return ResponseEntity.ok().body(new BillDTO(bill));
    }

    @PostMapping
    public ResponseEntity<BillDTO> insert(@RequestBody BillDTO billDTO){


        TrialBalance trialBalance = trialBalanceService.findById(billDTO.getTrialBalanceId());
        Bill bill = billService.fromDTO(billDTO);

        Bill newBill = billService.insert(bill);

        /* Add the new bill to the list */
        trialBalance.addBill(newBill);

        /* Update account values */
        trialBalance.setTotalCredit();
        trialBalance.setTotalDebt();
        trialBalance.setBalance();

        /* Update the TrialBalance*/
        // TODO: update the trialBalance


        return ResponseEntity.ok().body(new BillDTO(newBill));
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<BillDTO> update(@RequestBody Bill bill, @PathVariable String id){

        // TODO: It is not updating
        bill.setId(id);
        bill = billService.update(bill);
        return ResponseEntity.ok().body(new BillDTO(bill));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id){

        // TODO: check if the bill exists
        Bill bill = billService.findById(id);

        TrialBalance trialBalance = trialBalanceService.findById(bill.getTrialBalanceId());

        billService.delete(id);

        /* Disconnect the Bill from its TrialBalance */
        //trialBalance.getBills().remove(trialBalance.getBills().indexOf(bill));

        return ResponseEntity.noContent().build();
    }
}