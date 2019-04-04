package com.dvbispo.personalbudget.config;

import com.dvbispo.personalbudget.dao.BalancedBudgetRepository;
import com.dvbispo.personalbudget.dao.BillRepository;
import com.dvbispo.personalbudget.dao.TrialBalanceRepository;
import com.dvbispo.personalbudget.entity.BalancedBudget;
import com.dvbispo.personalbudget.entity.Bill;
import com.dvbispo.personalbudget.entity.TrialBalance;
import com.dvbispo.personalbudget.entity.enums.BillType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class Instantiation implements CommandLineRunner {

    @Autowired
    private BillRepository billRepository;
    @Autowired
    private TrialBalanceRepository trialBalanceRepository;
    @Autowired
    private BalancedBudgetRepository balancedBudgetRepository;

    @Override
    public void run(String... args) throws Exception {

        balancedBudgetRepository.deleteAll();
        trialBalanceRepository.deleteAll();
        billRepository.deleteAll();

        Bill bill1 = new Bill(null, "Salário",2019,4,5, 4763.65, BillType.DEBT);
        bill1.addNotes("Desconto da farmácia em folha");
        Bill bill2 = new Bill(null, "Conta de luz",2019,4,11, 56.34, BillType.CREDIT);
        Bill bill3 = new Bill(null, "Internet",2019,04, 3, 136.56, BillType.CREDIT);
        Bill bill4 = new Bill(null, "Combustível",2019,04, 6, 118.78, BillType.CREDIT);
        Bill bill5 = new Bill(null, "Dividendos",2019,04, 7, 456.19, BillType.DEBT);
        bill5.addNotes("FESA4");

        Bill bill6 = new Bill(null, "Salário",2019,5, 5, 5782.46, BillType.DEBT);
        bill6.addNotes("Desconto da farmácia em folha");
        Bill bill7 = new Bill(null, "Conta de luz", 2019,5,11, 78.23, BillType.CREDIT);
        Bill bill8 = new Bill(null, "Internet", 2019,5,3, 142.35, BillType.CREDIT);
        Bill bill9 = new Bill(null, "Combustível", 2019,5,22, 133.88, BillType.CREDIT);
        Bill bill10 = new Bill(null, "Dividendos", 2019,5,17, 1456.19, BillType.DEBT);
        bill10.addNotes("TRPL4");


        TrialBalance trialBalance1 = new TrialBalance(null, 2019,4);
        TrialBalance trialBalance2 = new TrialBalance(null, 2019, 5);
        trialBalance2.addNote("Férias na Europa");

        BalancedBudget balancedBudget1 = new BalancedBudget(null, 2019);

        trialBalance1.addBill(bill1);
        trialBalance1.addBill(bill2);
        trialBalance1.addBill(bill3);
        trialBalance1.addBill(bill4);
        trialBalance1.addBill(bill5);

        trialBalance2.addBill(bill6);
        trialBalance2.addBill(bill7);
        trialBalance2.addBill(bill8);
        trialBalance2.addBill(bill9);
        trialBalance2.addBill(bill10);

        balancedBudget1.addTrialBalance(trialBalance1);
        balancedBudget1.addTrialBalance(trialBalance2);

        billRepository.saveAll(Arrays.asList(bill1, bill2, bill3, bill4, bill5, bill6, bill7, bill8, bill9, bill10));
        trialBalanceRepository.saveAll(Arrays.asList(trialBalance1, trialBalance2));



        billRepository.saveAll(Arrays.asList(bill1));
        trialBalanceRepository.saveAll(Arrays.asList(trialBalance1));
        balancedBudgetRepository.saveAll(Arrays.asList(balancedBudget1));
    }
}
