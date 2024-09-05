package com.company;

import java.util.ArrayList;

public class Job {
    String nume_job;
    String nume_companie;
    boolean disponibilitate;
    Constraint<Integer> absolvire;
    Constraint<Integer> ani_experienta;
    Constraint<Double> media_academica;
    ArrayList<User> candidati = new ArrayList<>();
    int nr_angajati_nevoie;
    double salariul_primit;

    public Job (String nume_companie, String nume_job, boolean disponibilitate, Constraint<Integer> absolvire,Constraint<Integer> ani_experienta, Constraint<Double> media_academica, int nr_angajati_nevoie, double salariul_primit){
        this.nume_companie = nume_companie;
        this.nume_job = nume_job;
        this.disponibilitate = disponibilitate;
        this.absolvire = absolvire;
        this.ani_experienta = ani_experienta;
        this.media_academica= media_academica;
        this.nr_angajati_nevoie = nr_angajati_nevoie;
        this.salariul_primit = salariul_primit;
    }

    public void appply ( User user){
        Application apl = Application.getInstance();
        Company company = apl.getCompany(nume_companie);
        Recruiter rec = company.getRecruiter(user);
        rec.evaluate(this,user);
        candidati.add(user);
        Application application = Application.getInstance();
        application.getCompany(nume_companie).addObserver(user);
    }

    public  boolean meetsRequirments(User user){
        if( (absolvire.sup != null &&  user.getGraduationYear() != null   && absolvire.sup < user.getGraduationYear()) || (absolvire.inf != null &&  user.getGraduationYear() != null && absolvire.inf > user.getGraduationYear()) || (absolvire.sup != null &&  user.getGraduationYear() == null) )
            return false;
        else if (  (ani_experienta.sup != null &&  ani_experienta.sup < user.ani_de_experienta()) || ( ani_experienta.inf != null && ani_experienta.inf > user.ani_de_experienta()))
            return false;
        else if ( (media_academica.sup != null && media_academica.sup.compareTo(user.medie_de_finalizare()) < 0 )|| (media_academica.inf != null && media_academica.inf.compareTo(user.medie_de_finalizare()) > 0 ))
            return false;
        else
            return true;
    }
    public String toString(){
        return nume_job;
    }
}
