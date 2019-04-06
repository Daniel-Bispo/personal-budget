package com.dvbispo.personalbudget.repository;

import com.dvbispo.personalbudget.domain.Bill;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BillRepository extends MongoRepository<Bill, String> {

}
