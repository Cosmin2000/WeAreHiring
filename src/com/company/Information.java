package com.company;

import java.util.ArrayList;

public class Information {
    private String nume;
    private String prenume;
    private String telefon;
    private String email;
    private String data_nasterii;
    private String sex;
    private ArrayList<ArrayList<String>> limbi;

    public Information (String nume, String prenume, String telefon,String email, String data_nasterii, String sex,ArrayList<ArrayList<String>> limbi){
         this.nume = new String(nume);
         this.prenume = new String(prenume);
         this.telefon = new String(telefon);
         this.email = new String(email);
         this.data_nasterii = new String(data_nasterii);
         this.sex = new String(sex);
         this.limbi = limbi;
    }
    public void setName(String nume) {
        this.nume = nume;
    }
    public void setFirstName(String prenume) {
        this.prenume = prenume;
    }
    public void setTelefon (String telefon) {
        this.telefon = telefon;
    }
    public void setData(String data_nasterii){
        this.data_nasterii = data_nasterii;
    }
    public void setSex(String sex){
        this.sex = new String(sex);
    }

    public String getName() {
        return nume;
    }
    public String getFirstName() {
        return prenume;
    }
    public String getTelefon () {
        return telefon;
    }
    public String getData(){
        return data_nasterii;
    }
    public String getSex(){
        return sex;
    }
    public String getEmail() {
        return email;
    }
    public ArrayList<ArrayList<String>> getLanguages() {
        return limbi;
    }

    public String toString(){
        return "Name "+getName()+"\n"+"Surname "+ getFirstName()+"\n"+"Birth Date "+ getData()+"\n"+"Genre "+ getSex()+"\n"+"Phone "+ getTelefon()+"\n"+"Email "+ getEmail();
    }
}
