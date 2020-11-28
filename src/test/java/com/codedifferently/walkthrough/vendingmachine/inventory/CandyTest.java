package com.codedifferently.walkthrough.vendingmachine.inventory;

import org.junit.Assert;
import org.junit.Test;


public class CandyTest {


    @Test
    public void constructorTest(){

        // Given
        String name = "Dove Dark Chocolate";
        Double price = 1.00;
      //  DecimalFormat df2 = new DecimalFormat("#.##");
        Candy candyRef = new Candy("Dove Dark Chocolate", price);

        // When
        String expected = "Dove Dark Chocolate " + price;
        String actual = candyRef.toString();

        // Then
        Assert.assertEquals( "Constructor test", expected, actual);
    }
    @Test
    public void messageTest(){
        // Given
        Candy candyRef = new Candy();

        // When
        String expected = "Munch Munch, Yum!";
        String actual = candyRef.message();

        // Then
        Assert.assertEquals(expected,actual);
    }
}
