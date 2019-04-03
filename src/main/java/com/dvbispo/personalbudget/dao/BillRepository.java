package com.dvbispo.personalbudget.dao;

import com.dvbispo.personalbudget.entity.Bill;
import org.springframework.data.repository.CrudRepository;

public interface BillRepository extends CrudRepository<Bill,String> {

}
