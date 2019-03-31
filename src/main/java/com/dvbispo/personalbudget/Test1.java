package com.dvbispo.personalbudget;

import com.dvbispo.personalbudget.domain.Bill;
import com.dvbispo.personalbudget.domain.TrialBalance;
import com.dvbispo.personalbudget.domain.enums.BillType;

import java.util.Date;

public class Test1 {

    public static void main(String... args){



        Bill bill1 = new Bill(1, "Conta de luz", new Date(), 56.32, BillType.CREDIT);
        bill1.setNotes("Ligar para a central");

        Bill bill2 = new Bill(2, "Faxina", new Date(), 150.00, BillType.CREDIT);
        bill2.setNotes("Solicitada uma faxina extra");

        Bill bill3 = new Bill(3, "Rendimentos", new Date(), 5600.00, BillType.DEBT);

        TrialBalance tb = new TrialBalance(1);

        tb.addBill(bill1);
        tb.addBill(bill2);
        tb.addBill(bill3);
        tb.addNote("Mês de férias");
        System.out.println(tb);


    }
}
