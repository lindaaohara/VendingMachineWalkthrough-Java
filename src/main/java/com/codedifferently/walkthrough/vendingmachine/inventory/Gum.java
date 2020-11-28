package com.codedifferently.walkthrough.vendingmachine.inventory;
//public class Gum is an extension of the abstract class Product
public class Gum extends Product{

/*The abstract Product class demands a String name, a Double price, and a
 String msg to make a product.  name & price are treated the same for all
 products as indicated by super but each individual product gets its own message.
 */
 //I think this should be name, not nameIn; Faith says it doesn't matter and the tests pass
    public Gum(String nameIn, Double priceIn){
        super(nameIn, priceIn);
        msg = "Chew Chew, Yum!";
    }
//this creates a new object of type Gum.  It's needed for the message test to run
//but am unclear why
    public Gum(){
        this("Rhino Chew", 0.0);
    }
//this is necessary because the abstract Product class doesn't return any
//message and you want Gum to return the msg defined above.
    @Override
    public String message() {
        return msg;
    }
}
