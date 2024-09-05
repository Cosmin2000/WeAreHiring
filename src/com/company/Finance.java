package com.company;

public class Finance extends Department {
    @Override
    public double getTotalSalaryBudget() {
        double total = 0.0;
        for ( Employee employee: getEmployees() ){
            int luni = 0;
            for(Experience exp : employee.resume.getExperiences()) {
                if ( exp.compania.equals(employee.nume_companie)){
                    luni = exp.months_of_experience();
                    break;
                }
            }
            if (luni < 12) {
                total += ((double) 100/90)*employee.getSalariul();
            }
            else
                total += ((double) 100/84)*employee.getSalariul();
        }
        return total;
    }
}
