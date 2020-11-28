package com.codedifferently.walkthrough.vendingmachine.inventory;

public class Chips  extends Product{

    public Chips(String name, Double price){
        super(name, price);
        msg = "Crunch Crunch, Yum!";
    }

    public Chips() {
        this ("Smart Pop", 1.50);
    }

    @Override
    public String message() {
        return msg;

    }
}
