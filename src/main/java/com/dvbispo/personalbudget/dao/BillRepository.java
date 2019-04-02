package com.dvbispo.personalbudget.dao;

import com.dvbispo.personalbudget.entity.Bill;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BillRepository extends MongoRepository<Bill,String> {

}
