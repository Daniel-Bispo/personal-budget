package com.dvbispo.personalbudget.resource;

import com.dvbispo.personalbudget.domain.BalancedBudget;
import com.dvbispo.personalbudget.service.BalancedBudgetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/balancedbudgets")
public class BalancedBudgetResource {

    @Autowired
    private BalancedBudgetService service;

    @GetMapping
    public ResponseEntity<List<BalancedBudget>> findAll(){

        List<BalancedBudget> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }
}
