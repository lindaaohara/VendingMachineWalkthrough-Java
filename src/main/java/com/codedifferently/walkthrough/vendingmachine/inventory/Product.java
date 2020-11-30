package com.codedifferently.walkthrough.vendingmachine.inventory;

import java.text.DecimalFormat;

//setting up an abstract class called Product because we have a number of different products
//that will be similar in the way we want to handle them.
public abstract class Product {
//this says that all Products will include a String name, a Double price and a String msg
//that will be private--nobody can change them
    private String name;
    private Double price;
    protected String msg;

//this is the public definition of a Product; explicitly initializing the variables which
//otherwise could not be used since they were marked private above
    public Product(String name, Double priceIn){
        //the first "name" in purple refers to the private name on the Product object on line 7
        //the second "name" refers to the name parameter in the public constructor on line 14
        this.name = name;
        //the first "price" in purple refers to the private price on the Product object on line 8
        //priceIn" refers to the price parameter in the public constructor on line 14
        this.price = priceIn;
    }

    public String getName(){
        return name;
    }

    public Double getPrice(){
        return price;
    }

    @Override
    public String toString(){
        return name + " " + price;
    }

    public abstract String message();


}
