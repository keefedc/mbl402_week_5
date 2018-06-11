package edu.phoenix.mbl402.week5appdc6041;

import java.util.ArrayList;

public class SsgContainer <T>{

    private T object;
    private ArrayList<T> ssgList;

    public SsgContainer(T t) {
        this.object = t;
        this.ssgList = new ArrayList<>();
    }

    public ArrayList<T> getSsgList() {
        return ssgList;
    }

    public void addObject(T t) {
        this.ssgList.add(t);
    }

    public T getObject(int i) {
        return this.ssgList.get(i);
    }

}
