package com.company;

public class Notification {
    private String message;
    public Notification ( String message ) {
        this.message = message;
    }
    void  show (){
        System.out.println(message);
    }
}
