package com.dvbispo.personalbudget.repositories;

import com.dvbispo.personalbudget.domain.BalancedBudget;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BalancedBudgetRepository extends MongoRepository<BalancedBudget, Integer> {

}
