package com.company;

import java.time.LocalDate;
import java.time.Period;

public class Experience implements Comparable{
    LocalDate data_inceput, data_final;
    String pozitia;
    String compania;

    public Experience (LocalDate data_inceput, LocalDate data_final, String compania,String pozitia ) throws InvalidDatesException {
        if (data_final != null) {
            if (data_inceput.compareTo(data_final) > 0)
                throw new InvalidDatesException("Datele nu sunt valide cronologic");
        }
        this.data_inceput = LocalDate.of(data_inceput.getYear(),data_inceput.getMonth(),data_inceput.getDayOfMonth());
        if (data_final == null)
            this.data_final = null;
        else
            this.data_final = LocalDate.of(data_final.getYear(),data_final.getMonth(),data_final.getDayOfMonth());
        this.compania = compania;
        this.pozitia = pozitia;
    }
    int months_of_experience(){
        Period e;
        if (data_final == null)
            e = data_inceput.until(LocalDate.now());
        else
            e = data_inceput.until(data_final);
        return (int) (e.toTotalMonths());
    }
    @Override
    public int compareTo(Object o) {
        Experience exp =(Experience) o;
        if ( exp.data_final == null || data_final == null || exp.data_final.compareTo(data_final) == 0) {
            return compania.compareTo(exp.compania);
        }else
            return exp.data_final.compareTo(data_final);
        }
}
