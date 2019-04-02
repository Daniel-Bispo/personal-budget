package com.dvbispo.personalbudget.dao;

import com.dvbispo.personalbudget.entity.TrialBalance;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TrialBalanceRepository extends MongoRepository<TrialBalance, String> {

}
