package com.codedifferently.walkthrough.vendingmachine.inventory;

public class Candy extends Product{

    public Candy(String name, Double price){
        super(name, price);
        msg = "Munch Munch, Yum!";

    }

    public Candy() {
      this( "Snickers", 2.00);

    }

    @Override
    public String message() {

        return msg;
    }
}
