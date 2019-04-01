package com.dvbispo.personalbudget.config;

import com.dvbispo.personalbudget.domain.Bill;
import com.dvbispo.personalbudget.domain.enums.BillType;
import com.dvbispo.personalbudget.repositories.BillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class Instantiation implements CommandLineRunner {

    @Autowired
    private BillRepository billRepository;

    @Override
    public void run(String... args) throws Exception {

        billRepository.deleteAll();

        Bill bill1 = new Bill(null, "Salário", 5, 4763.65, BillType.DEBT);
        bill1.addNotes("Desconto da farmácia em folha");
        Bill bill2 = new Bill(null, "Conta de luz", 11, 56.34, BillType.CREDIT);
        Bill bill3 = new Bill(null, "Internet", 8, 136.56, BillType.CREDIT);
        Bill bill4 = new Bill(null, "Combustível", 22, 118.78, BillType.CREDIT);
        Bill bill5 = new Bill(null, "Dividendos", 10, 456.19, BillType.DEBT);
        bill5.addNotes("FESA4");

        billRepository.saveAll(Arrays.asList(bill1,bill2,bill3,bill4,bill5));
    }
}
