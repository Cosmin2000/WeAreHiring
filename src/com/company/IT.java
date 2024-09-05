package com.company;

public class IT extends Department{
    @Override
    public double getTotalSalaryBudget() {
        double total = 0.0;
        for ( Employee empl : getEmployees()){
            total += empl.getSalariul();
        }
        return total;
    }
}
