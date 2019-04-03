package com.dvbispo.personalbudget.dao;

import com.dvbispo.personalbudget.entity.BalancedBudget;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RestResource;

@RestResource(path = "balancedbudgets")
public interface BalancedBudgetRepository extends CrudRepository<BalancedBudget, String> {




}
