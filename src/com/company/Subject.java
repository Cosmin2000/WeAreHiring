package com.company;

public interface Subject {
    void addObserver(User user);
    void removeObserver(User c);
    void notifyAllObservers();
    void notifyObserver(Observer observer, Notification notification);
}
