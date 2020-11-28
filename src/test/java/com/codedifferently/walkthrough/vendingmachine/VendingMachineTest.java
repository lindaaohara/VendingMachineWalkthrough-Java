package com.codedifferently.walkthrough.vendingmachine;

import com.codedifferently.walkthrough.vendingmachine.inventory.Chips;
import com.codedifferently.walkthrough.vendingmachine.inventory.Drinks;
import com.codedifferently.walkthrough.vendingmachine.inventory.Product;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.Assert;

import java.util.Map;

public class VendingMachineTest {
    private final static Logger logger = Logger.getLogger(VendingMachineTest.class);

    @Test
    public void stockVendingMachineTest(){
        String[]sample = {"B4|Nice Red Wine|5.00|Drinks"};
        VendingMachine vendingMachine = new VendingMachine();
        vendingMachine.stockVendingMachine(sample);

        Product expectedDrinks = new Drinks("Nice Red Wine", 5.00);
        Map<String, Product> inventory = vendingMachine.getInventory();
        Product actualDrinks = inventory.get("B4");

        Assert.assertEquals(expectedDrinks.getName(), actualDrinks.getName());
    }

    @Test
    public void stockVendingMachineTest2(){
        String[]sample = {"B12|Doritos|2.00|Chips"};
        VendingMachine vendingMachine = new VendingMachine();
        vendingMachine.stockVendingMachine(sample);

        Product expectedChips = new Chips("Doritos", 2.00);
        Map<String, Product> inventory = vendingMachine.getInventory();
        Product actualChips = inventory.get("B12");

        Assert.assertEquals(expectedChips.getName(), actualChips.getName());
    }

    public static void main(String[] args) {
        logger.info("Start");
    }
}
