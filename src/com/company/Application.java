package com.company;

import java.util.ArrayList;
import java.util.List;

public class Application {
    private ArrayList<Company> companies = new ArrayList<>();
    private ArrayList<User> users = new ArrayList<>();

    private static Application instance = null;

    private Application (){}

    public static  Application getInstance() {

        if ( instance == null)
            instance = new Application();
        return instance;
    }

    //Determinarea companiilor care au fost înscrise în aplicatie;
    public ArrayList<Company> getCompanies(){
        return companies;
    }

    //Determinarea unei anumite companii în functie de numele furnizat;
    public Company getCompany(String name){
        for ( Company comp : companies) {
            if (comp.getNume().equals(name))
                return comp;
        }
        return  null;
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    //Adăugarea unei companii;
    public void add(Company company){
        companies.add(company);
    }

    //Adăugarea unui utilizator;
    public void add(User user){
        users.add(user);
    }
    //Stergerea unei companii – va întoarce false dacă compania nu există;
    public boolean remove(Company company){
        if (!companies.contains(company))
            return false;
        else
        {
            companies.remove(company);
            return true;
        }
    }
// S, tergerea unui utilizator – va întoarce false dacă utilizatorul nu există;
    public boolean remove(User user){
        if ( !users.contains(user))
            return false;
        else {
            users.remove(user);
            return true;
        }
    }
    //Determinarea joburile disponibile de la companiile pe care le preferă utilizatorul.
    public ArrayList<Job> getJobs(List<String> companies){
        ArrayList<Job> jobs_disponibile = new ArrayList<>();
        for ( String comp : companies) {
            for ( Company companie: this.companies) {
                if (companie.getNume().equals(comp))
                {
                    for (Department dep : companie.getDepartaments()) {
                        ArrayList<Job> job_uri = dep.getJobs();
                        jobs_disponibile.addAll(job_uri);
                    }
                    break;
                }
            }
        }
        return jobs_disponibile;
    }

}
