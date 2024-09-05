package com.company;

public class Management extends Department {
    @Override
    public double getTotalSalaryBudget() {
        Double total = 0.0;
        for ( Employee empl : getEmployees()) {
            total += empl.getSalariul();
        }
        total = ((double) 100/84) * total;
        return total;
    }
}
