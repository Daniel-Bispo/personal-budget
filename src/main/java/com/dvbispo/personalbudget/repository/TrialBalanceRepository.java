package com.dvbispo.personalbudget.repository;

import com.dvbispo.personalbudget.domain.TrialBalance;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RestResource;

@RestResource(exported = false)
public interface TrialBalanceRepository extends MongoRepository<TrialBalance, String> {

}
