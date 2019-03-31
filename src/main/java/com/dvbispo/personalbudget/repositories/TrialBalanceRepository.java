package com.dvbispo.personalbudget.repositories;

import com.dvbispo.personalbudget.domain.TrialBalance;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TrialBalanceRepository extends MongoRepository<TrialBalance, Integer> {
    
}
