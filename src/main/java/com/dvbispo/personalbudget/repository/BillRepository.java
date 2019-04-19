package com.dvbispo.personalbudget.repository;

import com.dvbispo.personalbudget.domain.Bill;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RestResource;

@RestResource(exported = false)
public interface BillRepository extends MongoRepository<Bill, String> {

}
