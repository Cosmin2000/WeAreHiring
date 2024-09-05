package com.company;

public class Employee extends Consumer{

    String nume_companie;
    Double salariul;

    public Employee(){}

    public Employee(String nume_companie, Double salariul) {
        this.nume_companie = nume_companie;
        this.salariul = salariul;
    }

    public void setNume_companie(String nume_companie) {
        this.nume_companie = nume_companie;
    }
    public void setSalariul(Double salariul){
        this.salariul = salariul;
    }
    public String getJobName(){
        String jobName = null;
        for ( Experience experience : resume.getExperiences()){
            if (experience.data_final == null)
                jobName = experience.pozitia;
        }
        return jobName;
    }

    public  Double getSalariul() {
        return salariul;
    }
}
