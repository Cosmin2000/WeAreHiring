package com.company;

import java.util.Comparator;

public class Comparator1 implements Comparator<Request<Job,Consumer>> {
    @Override
    public int compare(Request<Job,Consumer> o1, Request<Job,Consumer> o2) {
        return o2.getScore().compareTo(o1.getScore());
    }

}
