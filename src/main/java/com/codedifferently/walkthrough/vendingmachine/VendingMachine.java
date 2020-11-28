package com.codedifferently.walkthrough.vendingmachine;

import com.codedifferently.walkthrough.vendingmachine.inventory.*;
import com.codedifferently.walkthrough.vendingmachine.menu.Menu;
import org.apache.commons.io.IOUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class VendingMachine {
    private Scanner scan;
    private Map<String, Product> inventory;

    public VendingMachine() {
        this.scan = new Scanner(System.in);
        this.inventory = new HashMap<>();
    }

    public void init(){
        try {
            String output = readRawDataToString();
            String[] products = output.split("\n");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private Product makeProductFromString(String[] productData){
        Product product = null;
        String type = productData[3];
        String name = productData[1];
        Double price = Double.valueOf(productData[2]);
        switch(type){
            case "Candy":
                product = new Candy(name, price);
                break;
            case "Drinks":
                product = new Drinks(name, price);
                break;
            case "Chips":
                product = new Chips(name, price);
                break;
            default:
                break;

        }
        return product;
    }

    public void stockVendingMachine(String[] products){
        for (String product : products){
            String[] productData = product.split("\\|");
            String key = productData[0];
            Product item = makeProductFromString(productData);
            inventory.put(key, item);
        }
    }
    private String readRawDataToString() throws Exception{
        ClassLoader classLoader = getClass().getClassLoader();
        String result = IOUtils.toString(classLoader.getResourceAsStream("inventory.txt"));
        return result;

    }
    public void start() {
        init();
        System.out.println("Welcome to the Vending Machine");
        boolean flag = true;
        ArrayList<String> options = new ArrayList<>();
        options.add("(1) Display Vending Machine Items");
        options.add("(2) Purchase");
        options.add("(3) Exit");
        Menu menu = new Menu(options);
        while(flag){
            for(String option : menu.getOptions()){
                System.out.println(option);
            }
            String input = scan.next();

            if(input.equals("3")){
                flag = false;
                System.out.println("Goodbye");
            } else {
                System.out.println("Try again");
            }
        }
        scan.close();

    }
    public Map<String, Product> getInventory(){
        return this.inventory;
    }
    public static void main(String[] args) {
        VendingMachine vendingMachine = new VendingMachine();
        vendingMachine.start();

    }
}
