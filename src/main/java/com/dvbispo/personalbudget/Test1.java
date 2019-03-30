package com.dvbispo.personalbudget;

import com.dvbispo.personalbudget.domain.Bill;
import com.dvbispo.personalbudget.domain.enums.BillType;

import java.util.Date;

public class Test1 {

    public static void main(String... args){



        Bill bill1 = new Bill(1, "Conta de luz", new Date(), 56.32, BillType.CREDIT);
        bill1.setNotes("Ligar para a central");

        System.out.println(bill1);


    }
}
