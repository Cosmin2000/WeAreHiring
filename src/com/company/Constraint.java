package com.company;

public class Constraint<T> {
    T inf, sup;

    public Constraint( T inf, T sup) {
        this.inf = inf;
        this.sup = sup;
    }
}
