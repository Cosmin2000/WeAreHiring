package com.company;

import java.util.ArrayList;

public class User extends Consumer implements Observer {
    ArrayList<String> companies;

    public Employee convert(){
        Employee angajat = new Employee();
        angajat.resume = resume;
        angajat.close_friends = close_friends;
        return angajat;
    }
    public int ani_de_experienta () {
        int ani_experienta;
        int luni_de_experienta = 0;
        for (Experience exp : resume.getExperiences()){
            luni_de_experienta += exp.months_of_experience();
        }
        ani_experienta = luni_de_experienta/12;
        if ( luni_de_experienta%12 >= 3)
            ani_experienta++;
        return  ani_experienta;
    }
    public Double medie_de_finalizare () {
        Double medie = 0.0;
        for ( Education ed:resume.getEducations()){
            medie += ed.media_de_finalizare;
        }
        medie = medie / (resume.getEducations().size());
        return medie;
    }
    public Double getTotalScore() {
        int ani_experienta;
        int luni_de_experienta = 0;
        Double media = 0.0;
        for (Experience exp : resume.getExperiences()){
            luni_de_experienta += exp.months_of_experience();
        }
        ani_experienta = luni_de_experienta/12;
        if ( luni_de_experienta%12 >= 3)
            ani_experienta++;
        double ani_de_experienta = ani_experienta;
        for ( Education ed:resume.getEducations()){
            media += ed.media_de_finalizare;
        }
        media = media / (resume.getEducations().size());
        return  ani_de_experienta*1.5 + media;

    }

    @Override
    public void update(Notification notification) {
        notification.show();
    }
    public String toString (){
        return resume.getInfo().getFirstName() +" "+ resume.getInfo().getName();
    }
}
