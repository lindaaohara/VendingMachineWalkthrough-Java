package com.codedifferently.walkthrough.vendingmachine.inventory;

import org.junit.Assert;
import org.junit.Test;

public class ChipsTest {

    @Test
    public void constructorTest() {
        //Given
        String name = "Sun Baked Chips";
        Double price = 1.50;

        Chips chipsRef = new Chips("Sun Baked Chips", price);

        // When
        String expected = "Sun Baked Chips " + price;
        String actual = chipsRef.toString();

        //Then
        Assert.assertEquals("Constructor test", expected, actual);


    }
    @Test
    public void messageTest(){
        //Given
        Chips chipsRef = new Chips();
        //When
        String expected = "Crunch Crunch, Yum!";
        String actual = chipsRef.message();

        //Then
        Assert.assertEquals(expected, actual);
    }

}
