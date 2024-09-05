package com.company;

import java.util.ArrayList;

public abstract class Department {
    private ArrayList<Employee> employees;
    private ArrayList<Job> jobs;

    public Department(){
        employees = new ArrayList<>();
        jobs = new ArrayList<>();
    }
// Metodă abstractă care va returna bugetul total de salarii, după aplicarea taxelor;
    public abstract double getTotalSalaryBudget();
// Metodă care întoarce toate joburile deschise din departament;
    public ArrayList<Job> getJobs(){
        return jobs;
    }
// Metodă care adăuga un angajat în departament;
    public void add(Employee employee){
        employees.add(employee);
    }
// Metodă care sterge un angajat din departament;
    public void remove(Employee employee){
        employees.remove(employee);
    }
// Metodă care adaugă un job în departament;
    public void add(Job job){
        jobs.add(job);
        Application apl = Application.getInstance();
        apl.getCompany(job.nume_companie).notifyAllObservers();

    }
// Metodă care întoarce angajatii dintr-un departament;
    public ArrayList<Employee> getEmployees(){
        return employees;
    }
    public String toString(){
        if ( this instanceof IT)
            return "IT";
        else if ( this instanceof Management)
            return "Management";
        else if ( this instanceof Marketing)
            return "Marketing";
        else
            return "Finance";
    }
}
