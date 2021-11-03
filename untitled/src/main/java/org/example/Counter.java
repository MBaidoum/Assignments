package org.example;

public class Counter {
    private  int count = 0;

    public Counter(){

    }

    public int increment(){
        this.count++;
        return this.count;
    }

    public int decrement(){
        this.count--;
        return this.count;
    }
}
