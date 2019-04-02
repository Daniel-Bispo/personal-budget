package com.dvbispo.personalbudget.dao;

import com.dvbispo.personalbudget.entity.BalancedBudget;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BalancedBudgetRepository extends MongoRepository<BalancedBudget, String> {

}
