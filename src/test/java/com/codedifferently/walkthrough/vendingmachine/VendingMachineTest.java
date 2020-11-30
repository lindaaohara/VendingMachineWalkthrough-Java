package com.codedifferently.walkthrough.vendingmachine;

import com.codedifferently.walkthrough.vendingmachine.inventory.Chips;
import com.codedifferently.walkthrough.vendingmachine.inventory.Drinks;
import com.codedifferently.walkthrough.vendingmachine.inventory.Product;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class VendingMachineTest {
    private final static Logger logger = Logger.getLogger(VendingMachineTest.class);
    private Product product;

    @Test
    public void stockVendingMachineTest(){
        String[]sample = {"B4|Nice Red Wine|5.00|Drinks"};
        VendingMachine vendingMachine = new VendingMachine();
        vendingMachine.stockVendingMachine(sample);

        Product expectedDrinks = new Drinks("Nice Red Wine", 5.00);
        Map<String, ArrayList<Product>> inventory = vendingMachine.getInventory();
        Product actualDrinks = inventory.get("B4").get(0);

        assertEquals(expectedDrinks.getName(), actualDrinks.getName());
    }

    @Test
    public void stockVendingMachineTest2(){
        String[]sample = {"B12|Doritos|2.00|Chips"};
        VendingMachine vendingMachine = new VendingMachine();
        vendingMachine.stockVendingMachine(sample);

        Product expectedChips = new Chips("Doritos", 2.00);
        Map<String, ArrayList<Product>> inventory = vendingMachine.getInventory();
        Product actualChips = inventory.get("B12").get(0);

        assertEquals(expectedChips.getName(), actualChips.getName());
    }



    @Test
    public void giveChangeTest(){
        VendingMachine vendingMachine = new VendingMachine();
        Double balance = 4.0;

        Double expected = 4.0;
        vendingMachine.setBalance(balance);
        Double actual = vendingMachine.giveChange();

        assertEquals(expected, actual);

    }

    @Test
    public void getInventoryAsStringTest(){
        assertEquals("A1 : Doublemint 0.75 : Quantity 5\n" +
                "A2 : Coke 1.0 : Quantity 5\n" +
                "A3 : Popcorn 1.25 : Quantity 5\n" +
                "A4 : Snickers 2.0 : Quantity 5\n"+
                "A5 : Hersheys 2.5 : Quantity 5\n",
                new VendingMachine().getInventoryAsString());
    }
    public static void main(String[] args) {
        logger.info("Start");
    }
}
