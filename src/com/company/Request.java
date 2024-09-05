package com.company;

import java.text.DecimalFormat;

public class Request<K, V> {
    private K key;
    private V value1, value2;
    private Double score;
    public Request(K key, V value1, V value2, Double score) {
        this.key = key;
        this.value1 = value1;
        this.value2 = value2;
        this.score = score;
    }
    public K getKey() {
        return key;
    }
    public V getValue1() {
        return value1;
    }
    public V getValue2() {
        return value2;
    }
    public Double getScore() {
        return score;
    }
    public String toString() {
        DecimalFormat format = new DecimalFormat("#.##");
        return "For " + key + ", applied by " + value1 + ", evaluated by " + value2 + " with a score of " + format.format(score);
    }
}
