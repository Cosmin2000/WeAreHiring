package com.company;

public class Marketing extends Department{
    @Override
    public double getTotalSalaryBudget() {
        double total = 0.0;
        for (Employee employee : getEmployees()) {
            if( employee.getSalariul().compareTo(5000.0) > 0){
                total += ((double) 100/90)*employee.getSalariul();
            }else if ( employee.getSalariul().compareTo(3000.0) < 0) {
                total += employee.getSalariul();
            }
            else  {
                total += ((double) 100/84)*employee.getSalariul();
            }
        }
        return  total;
    }
}
