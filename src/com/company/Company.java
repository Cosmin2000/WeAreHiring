package com.company;

import java.util.ArrayList;
import java.util.Collections;

public class Company implements Subject{
    private String nume;
    private Manager manager;
    private ArrayList<Recruiter> recruiters;
    private ArrayList<Department> departaments;
     ArrayList<Observer> observers = new ArrayList<>();


//    Adăugarea unui nou departament în companie;
    public void add(Department department){
        departaments.add(department);
    }
    public Company ( Manager manager, String nume){
        this.nume = nume;
        this.manager = manager;
        departaments = new ArrayList<>();
        recruiters = new ArrayList<>();
    }
    public Manager getManager() {
        return manager;
    }

    public String getNume() {
        return nume;
    }

    public ArrayList<Recruiter> getRecruiters() {
        return recruiters;
    }

    public ArrayList<Department> getDepartaments() {
        return departaments;
    }

    // Adăugarea unui nou recruiter;
    public void add(Recruiter recruiter){
        recruiters.add(recruiter);
        for (Department department:departaments){
            if (department instanceof IT)
            {
                department.add(recruiter);
                break;
            }
        }
    }
// Adăugarea unui angajat într-un departament;
    public void add(Employee employee, Department department){
        for ( Department dep: departaments) {
            if ( dep.getClass().equals(department.getClass())){
                dep.add(employee);
                break;
            }
        }
    }
// Eliminarea unui angajat din companie;
    public void remove(Employee employee){
        Recruiter rec = (Recruiter)employee;
        if (recruiters.contains(rec))
            recruiters.remove(employee);
        for ( Department dep : departaments){
            ArrayList<Employee> employees = dep.getEmployees();
            employees.remove(employee);
        }
    }
    // Eliminarea unui departament din companie si a tuturor angajatilor care fac parte din departamentul respectiv;
    public void remove(Department department){
        for ( Department dep: departaments){
            if ( dep.getClass().equals(department.getClass())){
                for ( Employee empl : dep.getEmployees())
                    dep.getEmployees().remove(empl);
            }
        }
        departaments.remove(department);
    }
// Eliminarea unui recruiter;
    public void remove(Recruiter recruiter){
        if (recruiters.contains(recruiter)){
            recruiters.remove(recruiter);
            for (Department department: departaments){
                if (department.getEmployees().contains(recruiter))
                {
                    department.getEmployees().remove(recruiter);
                    break;
                }
            }
        }
    }
// Mutarea unui departament în alt departament si transferarea tuturor angajatilor;
    public void move(Department source, Department destination){
        destination.getEmployees().addAll(source.getEmployees());
        destination.getJobs().addAll(source.getJobs());
        departaments.remove(source);
    }
// Mutarea unui angajat dintr-un departament în alt departament;
    public void move(Employee employee, Department newDepartment){
        Department department = departaments.get(departaments.indexOf(newDepartment));
        department.add(employee);
        for (Department dep: departaments){
            ArrayList<Employee> employees = dep.getEmployees();
            employees.remove(employee);
        }
    }
// Verificarea existentei unui departament în companie;
    public boolean contains(Department department){
        return departaments.contains(department);
    }
// Verificare existentei unui angajat în companie;
    public boolean contains(Employee employee){
        Manager man = (Manager)employee;
        if (man.equals(manager))
            return true;
        for ( Department department:departaments){
            if(department.getEmployees().contains(employee))
                return  true;
        }
        return false;
    }
// Verificarea existentei unui recruiter în companie;
    public boolean contains(Recruiter recruiter){
        return recruiters.contains(recruiter);
    }
// Determinarea recruiter-ului potrivit pentru un utilizator;
    public Recruiter getRecruiter(User user){
        ArrayList<Integer> dist = new ArrayList<>();
        Recruiter match_recruiter = null;
        ArrayList<Recruiter> recruiters_potriviti = new ArrayList<>();
        for ( Recruiter rec: recruiters){
            dist.add(user.getDegreeInFriendship(rec));
        }
        if ( dist.contains(-1)){
            for ( int i = 0 ; i < dist.size();i++){
                if ( dist.get(i) == -1){
                    recruiters_potriviti.add(recruiters.get(i));
                }
            }
            Double rating_maximum = 0.0;
            for ( Recruiter recruiter: recruiters_potriviti) {
                if (recruiter.getRating().compareTo(rating_maximum) > 0){
                    rating_maximum = recruiter.getRating();
                    match_recruiter = recruiter;
                }
            }
        }
        else {
            int max1 = Collections.max(dist);
            for (int i = 0; i < dist.size(); i++) {
                if (dist.get(i) == max1)
                    recruiters_potriviti.add(recruiters.get(i));
            }
            Double ratin_max1 = 0.0;
            for (Recruiter recr : recruiters_potriviti) {
                if (recr.getRating().compareTo(ratin_max1) > 0)
                {
                    ratin_max1 = recr.getRating();
                    match_recruiter = recr;
                }
            }

        }
        return  match_recruiter;
    }
// Determinarea job-urilor disponibile dintr-o companie (cele care nu au fost deja închise);
    public ArrayList<Job> getJobs(){
        ArrayList<Job> jobs = new ArrayList<>();
        for ( Department dep: departaments){
            jobs.addAll(dep.getJobs());
        }
        return jobs;
    }

    @Override
    public void addObserver(User user) {
        if (!observers.contains(user))
        observers.add(user);
    }

    @Override
    public void removeObserver(User c) {
        observers.remove(c);
    }

    @Override
    public void notifyAllObservers() {
        for ( Observer observer : observers){
            observer.update(new Notification("Please check your email, a job has been added or closed"));
        }

    }

    @Override
    public void notifyObserver(Observer observer,Notification notification) {
        observer.update(notification);
    }
    public String toString(){
        return nume;
    }
}
