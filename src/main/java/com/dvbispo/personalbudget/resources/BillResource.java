package com.dvbispo.personalbudget.resources;

import com.dvbispo.personalbudget.domain.Bill;
import com.dvbispo.personalbudget.domain.enums.BillType;
import com.dvbispo.personalbudget.services.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping(value = "/bills")
public class BillResource {

    @Autowired
    private BillService service;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Bill>> findAll(){

        Bill bill1 = new Bill(1, "Conta de Luz", 11, 56.42, BillType.DEBT);
        Bill bill2 = new Bill(2, "Salário", 5, 4756.81, BillType.CREDIT);

        List<Bill> billList = new ArrayList<>();
        billList.addAll(Arrays.asList(bill1,bill2));

        return ResponseEntity.ok().body(billList);
    }
}
