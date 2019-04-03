package com.dvbispo.personalbudget.dao;

import com.dvbispo.personalbudget.entity.TrialBalance;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RestResource;

@RestResource(path = "trialbalances")
public interface TrialBalanceRepository extends CrudRepository<TrialBalance, String> {

}
