package com.company;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

public class Education implements Comparable{
     LocalDate data_inceput;
     LocalDate data_sfarsit;
     String institutie;
     String nivel_de_educatie;
     Double media_de_finalizare;

    public Education () {}

    public  Education(LocalDate data_inceput, LocalDate data_sfarsit, String institutie, String nivel_de_educatie, Double media_de_finalizare) throws InvalidDatesException{
        if (data_inceput != null && data_sfarsit != null){
            if ( data_inceput.compareTo(data_sfarsit) > 0)
                throw new InvalidDatesException("Datele nu sunt corecte cronologic");
        }
        if (data_inceput != null)
            this.data_inceput = LocalDate.of(data_inceput.getYear(),data_inceput.getMonth(),data_inceput.getDayOfMonth());
        else
            this.data_inceput = null;
        if (data_sfarsit != null)
            this.data_sfarsit = LocalDate.of(data_sfarsit.getYear(),data_sfarsit.getMonth(),data_sfarsit.getDayOfMonth());
        else
            this.data_sfarsit= null;
        this.media_de_finalizare = media_de_finalizare;
        this.institutie = new String(institutie);
        this.nivel_de_educatie = new String(nivel_de_educatie);
    }

    @Override
    public int compareTo(Object o) {
        Education ed =(Education)o;
        if ( data_sfarsit == null || ed.data_sfarsit == null) {
            return data_inceput.compareTo(ed.data_inceput);
        }
        else {
            if (ed.data_sfarsit.compareTo(data_sfarsit) == 0) {
                return ed.media_de_finalizare.compareTo(media_de_finalizare);
            }else
                return ed.data_sfarsit.compareTo(data_sfarsit);
        }
    }

    public static void main(String[] args) throws InvalidDatesException {
        Education edu = new Education( LocalDate.of(2013,1,3),LocalDate.of(2015,1,3),"Google","Master",10.00 );
        //edu.data_sfarsit = LocalDate.of(2013,3,3);
        //edu.data_inceput = LocalDate.of(2012,4,1);
        //System.out.println(edu.data_sfarsit.getYear());
        LocalDate proba = LocalDate.parse("12.02.2015", DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        System.out.println(proba.format(DateTimeFormatter.ofPattern("dd.MM.yyyy")));
        Period e = edu.data_inceput.until(LocalDate.now());
        int ani = (int) (e.toTotalMonths()/12);
        int luni = (int) (e.toTotalMonths() % 12);
        if (luni > 3)
            ani++;

        //System.out.println(e.toTotalMonths());
    }
}
