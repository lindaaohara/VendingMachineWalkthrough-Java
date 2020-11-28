package com.codedifferently.walkthrough.vendingmachine.inventory;

import org.junit.Assert;
import org.junit.Test;

public class DrinksTest {

    @Test
    public void constructorTest(){

        //Given
        String name = "Nice Red Wine";
        Double price = 5.00;
        Drinks drinksRef = new Drinks("Nice Red Wine", price);

        // When
        String expected = "Nice Red Wine " + price;
        String actual = drinksRef.toString();

        //Then
        Assert.assertEquals("Constructor test", expected, actual);

    }
    @Test
    public void messageTest(){
        //Given
        Drinks drinksRef= new Drinks();

        // When
        String expected = "Glug Glug, Yum!";
        String actual = drinksRef.message();

        // Then
        Assert.assertEquals(expected, actual);
    }

}
