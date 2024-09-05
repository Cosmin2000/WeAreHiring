package com.company;

public class Recruiter  extends Employee{
    private Double rating = 5.0;
    public Recruiter(String company,double salariu){
        super(company,salariu);
    }

    public  int evaluate (Job job, User user){
        double evaluare = (rating*user.getTotalScore());
        rating += 0.1;
        Request<Job,Consumer> request = new Request<>  (job,user,this,evaluare);
        Application application = Application.getInstance();
        Company comp = application.getCompany(nume_companie);
        comp.getManager().getCereri_angajare().add(request);
        return (int)evaluare;
    }

    public Double getRating() {
        return rating;
    }
}
