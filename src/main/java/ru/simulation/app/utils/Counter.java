package ru.simulation.app.utils;

public class Counter {

    private int count;
    private static Counter instance;

    private Counter() {
        this.count = 0;
    }

    public static synchronized Counter getInstance() {
        if (instance == null) {
            instance = new Counter();
        }
        return instance;
    }

    public int getCount() {
        return count;
    }

    public void increment() {
        count++;
    }
}
