package com.codedifferently.walkthrough.vendingmachine.inventory;
//the package defines the family relation of the object
//importing the junit bits you need to run the tests
import org.junit.Assert;
import org.junit.Test;

//GumTest is a class definition
public class GumTest {

//this is testing the constructor setup whether setting the name and price will return the name & price in a string

    @Test
    public void constructorTest(){
        // Given
        //setting the value of name which is of type String to "Big Red"
        String name = "Big Red";

        Double price = 0.25;
        //gumRef is of type Gum which is equal to a new Gum object.  This new Gum object contains values (name and price)
        //that the constructor method will use to construct the product

        Gum gumRef = new Gum("Big Red", price);

        // When
        String expected = "Big Red " + price;
        //the actual is calling the .toString() of Gum and the .toString() of product because of polymorphism, which will
        //return name and price
        String actual = gumRef.toString();
        // Then
        Assert.assertEquals("Constructor test",expected, actual);
    }
//testing that the right message for gum is returned
    @Test
    public void messageTest(){
        //Given
        Gum gumRef = new Gum();

        // When
        String expected = "Chew Chew, Yum!";
        String actual = gumRef.message();

        // Then

        Assert.assertEquals(expected, actual);
    }
}
