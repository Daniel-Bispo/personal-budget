package com.dvbispo.personalbudget.resources;

import com.dvbispo.personalbudget.domain.Bill;
import com.dvbispo.personalbudget.services.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/bills")
public class BillResource {

    @Autowired
    private BillService service;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Bill>> findAll(){

        List<Bill> billList = service.findAll();

        return ResponseEntity.ok().body(billList);
        //return null;
    }
}