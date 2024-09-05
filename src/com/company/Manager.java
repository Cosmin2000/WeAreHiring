package com.company;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.TreeSet;

public class Manager extends Employee{
     private HashSet<Request<Job,Consumer>> cereri_angajare = new HashSet<>();

    public Manager (String nume_companie,Double salariul){
        super(nume_companie,salariul);
    }
    public HashSet<Request<Job,Consumer>> getCereri_angajare(){
        return cereri_angajare;
    }
    public  void process( Job job){
        TreeSet<Request<Job,Consumer>> cereri_job = new TreeSet<>(new Comparator1());
        Iterator<Request<Job,Consumer>> itr = cereri_angajare.iterator();
        while ( itr.hasNext()){
            Request<Job,Consumer> req = itr.next();
            if (req.getKey().equals(job)) {
                   cereri_job.add(req);
                   itr.remove();
            }
        }
        int nr_user_angajati = 0;
        Iterator<Request<Job,Consumer>> it = cereri_job.iterator();
        Department department = null;
        while (it.hasNext() && (nr_user_angajati < job.nr_angajati_nevoie) ) {
            Request<Job,Consumer> requ = it.next();
            User user = (User) requ.getValue1();
            Application apl = Application.getInstance();
            if ( apl.getUsers().contains(user)){
                if (job.meetsRequirments(user)){
                    apl.getUsers().remove(user);
                    job.candidati.remove(user);
                    Employee employee = user.convert();
                    employee.setSalariul(job.salariul_primit);
                    employee.setNume_companie(job.nume_companie);
                    for ( String comp : user.companies){
                        Company compan = apl.getCompany(comp);
                        compan.removeObserver(user);
                    }
                    Company company = apl.getCompany(nume_companie);
                    for (Department dep : company.getDepartaments()){
                        ArrayList<Job> jobs_dep = dep.getJobs();
                        if ( jobs_dep.contains(job)){
                            department = dep;
                            dep.add(employee);
                            break;
                        }
                    }
                    nr_user_angajati++;
                }
            }
            else
                job.candidati.remove(user);
        }
        job.disponibilitate = false;
        job.nr_angajati_nevoie = 0;
        if ( department != null)
            department.getJobs().remove(job);
        Application apl = Application.getInstance();
        for (User usr : job.candidati){
            apl.getCompany(nume_companie).notifyObserver(usr,new Notification("Sorry, your request were rejected from "+ nume_companie +" Please try again later"));
        }
        apl.getCompany(nume_companie).notifyAllObservers();
    }
    public String toString (){
        return resume.getInfo().getFirstName()+" " +resume.getInfo().getName()+" from "+nume_companie;
    }
}
