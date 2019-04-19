package com.dvbispo.personalbudget.config;

import com.dvbispo.personalbudget.domain.BalancedBudget;
import com.dvbispo.personalbudget.domain.Bill;
import com.dvbispo.personalbudget.domain.TrialBalance;
import com.dvbispo.personalbudget.domain.enums.BillType;
import com.dvbispo.personalbudget.repository.BalancedBudgetRepository;
import com.dvbispo.personalbudget.repository.BillRepository;
import com.dvbispo.personalbudget.repository.TrialBalanceRepository;
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

        billRepository.deleteAll();
        trialBalanceRepository.deleteAll();
        balancedBudgetRepository.deleteAll();

        BalancedBudget balancedBudget1 = new BalancedBudget(null, 2019);
        balancedBudgetRepository.saveAll(Arrays.asList(balancedBudget1));
        balancedBudget1 = balancedBudgetRepository.findById(balancedBudget1.getId()).get(); // id update

        TrialBalance trialBalance1 = new TrialBalance(null, 2019,4,null);
        TrialBalance trialBalance2 = new TrialBalance(null, 2019, 5,null);
        trialBalance2.setNote("Férias na Europa");
        trialBalanceRepository.saveAll(Arrays.asList(trialBalance1, trialBalance2));
        trialBalance1 = trialBalanceRepository.findById(trialBalance1.getId()).get(); // id update
        trialBalance2 = trialBalanceRepository.findById(trialBalance2.getId()).get(); // id update

        /* connect the TrialBalance to its BalancedBudget */
        balancedBudget1.addTrialBalance(trialBalance1);
        balancedBudget1.addTrialBalance(trialBalance2);

        Bill bill1 = new Bill(null, "Salário",2019,4,5, 4763.65, BillType.DEBT,trialBalance1.getId(),null);
        bill1.setPayed(true);
        bill1.setNotes("Desconto da farmácia em folha");
        bill1.setStatus();


        Bill bill2 = new Bill(null, "Conta de luz",2019,4,11, 56.34, BillType.CREDIT, trialBalance1.getId(), null);
        bill2.setPayed(false);
        bill2.setStatus();

        Bill bill3 = new Bill(null, "Internet",2019,4, 3, 136.56, BillType.CREDIT, trialBalance1.getId(), null);
        bill3.setPayed(false);
        bill3.setStatus();

        Bill bill4 = new Bill(null, "Combustível",2019,4, 6, 118.78, BillType.CREDIT, trialBalance1.getId(), null);
        bill4.setPayed(true);
        bill4.setStatus();

        Bill bill5 = new Bill(null, "Dividendos",2019,4, 7, 456.19, BillType.DEBT, trialBalance1.getId(), null);
        bill5.setPayed(false);
        bill5.setNotes("FESA4");
        bill5.setStatus();

        Bill bill6 = new Bill(null, "Salário",2019,5, 5, 5782.46, BillType.DEBT, trialBalance2.getId(), null);
        bill6.setPayed(false);
        bill6.setNotes("Desconto da farmácia em folha");
        bill6.setStatus();

        Bill bill7 = new Bill(null, "Conta de luz", 2019,5,11, 78.23, BillType.CREDIT, trialBalance2.getId(), null);
        bill7.setPayed(false);
        bill7.setStatus();

        Bill bill8 = new Bill(null, "Internet", 2019,5,3, 142.35, BillType.CREDIT, trialBalance2.getId(), null);
        bill8.setPayed(true);
        bill8.setStatus();

        Bill bill9 = new Bill(null, "Combustível", 2019,5,22, 133.88, BillType.CREDIT, trialBalance2.getId(), null);
        bill9.setPayed(false);
        bill9.setStatus();

        Bill bill10 = new Bill(null, "Dividendos", 2019,5,17, 1456.19, BillType.DEBT, trialBalance2.getId(), null);
        bill10.setPayed(false);
        bill10.setNotes("TRPL4");
        bill10.setStatus();


        billRepository.saveAll(Arrays.asList(bill1, bill2, bill3, bill4, bill5, bill6, bill7, bill8, bill9, bill10));

        bill1 = billRepository.findById(bill1.getId()).get();
        bill2 = billRepository.findById(bill2.getId()).get();
        bill3 = billRepository.findById(bill3.getId()).get();
        bill4 = billRepository.findById(bill4.getId()).get();
        bill5 = billRepository.findById(bill5.getId()).get();
        bill6 = billRepository.findById(bill6.getId()).get();
        bill7 = billRepository.findById(bill7.getId()).get();
        bill8 = billRepository.findById(bill8.getId()).get();
        bill9 = billRepository.findById(bill9.getId()).get();
        bill10 = billRepository.findById(bill10.getId()).get();


        trialBalance1.addBill(bill1);
        trialBalance1.addBill(bill2);
        trialBalance1.addBill(bill3);
        trialBalance1.addBill(bill4);
        trialBalance1.addBill(bill5);
        trialBalance1.setTotalDebt();
        trialBalance1.setTotalCredit();
        trialBalance1.setBalance();

        trialBalance2.addBill(bill6);
        trialBalance2.addBill(bill7);
        trialBalance2.addBill(bill8);
        trialBalance2.addBill(bill9);
        trialBalance2.addBill(bill10);
        trialBalance2.setTotalDebt();
        trialBalance2.setTotalCredit();
        trialBalance2.setBalance();

        trialBalanceRepository.saveAll(Arrays.asList(trialBalance1, trialBalance2));

        balancedBudget1.addTrialBalance(trialBalance1);
        balancedBudget1.addTrialBalance(trialBalance2);

        balancedBudgetRepository.saveAll(Arrays.asList(balancedBudget1));
    }
}
