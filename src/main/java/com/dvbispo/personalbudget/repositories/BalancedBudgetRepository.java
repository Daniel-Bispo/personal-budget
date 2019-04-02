package com.dvbispo.personalbudget.repositories;

import com.dvbispo.personalbudget.domain.BalancedBudget;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BalancedBudgetRepository extends MongoRepository<BalancedBudget, String> {

}
