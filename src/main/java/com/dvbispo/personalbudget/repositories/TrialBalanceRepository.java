package com.dvbispo.personalbudget.repositories;

import com.dvbispo.personalbudget.domain.TrialBalance;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrialBalanceRepository extends MongoRepository<TrialBalance, String> {

}
