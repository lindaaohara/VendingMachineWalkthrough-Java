package com.codedifferently.walkthrough.vendingmachine;

import com.codedifferently.walkthrough.vendingmachine.inventory.*;
import com.codedifferently.walkthrough.vendingmachine.menu.Menu;
import org.apache.commons.io.IOUtils;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class VendingMachine {
    private Scanner scan;
    private Map<String, ArrayList<Product>> inventory;
    public int quantity = 5;
    public Double balance = 0.0;

    public Double getBalance(){
        return balance;
    }

    public VendingMachine() {
        this.scan = new Scanner(System.in);
        this.inventory = new HashMap<>();
        this.init();
    }

    public void init(){
        try {
            String output = readRawDataToString();
            String[] products = output.split("\n");
            stockVendingMachine(products);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private ArrayList<Product> makeProductFromString(String[] productData){
        Product product = null;
        String type = productData[3];
        type = type.trim();
        //System.out.println("Type: " + type);
        String name = productData[1];
        Double price = Double.valueOf(productData[2]);
        ArrayList<Product> listOfProduct = new ArrayList<>();
        switch(type){
            case "Candy":
                for(int i = 0;  i < quantity; i++){
                    listOfProduct.add(new Candy(name,price));
                }

                break;
            case "Drinks":
                for(int i = 0;  i < quantity; i++){
                    listOfProduct.add(new Drinks(name,price));
                }
                break;
            case "Chips":
                for(int i = 0;  i < quantity; i++){
                    listOfProduct.add(new Chips(name,price));
                }
                break;
            case "Gum":
                for(int i = 0;  i < quantity; i++){
                    listOfProduct.add(new Gum(name,price));
                }
                break;
            default:
                break;

        }
        return listOfProduct;
    }

    public void stockVendingMachine(String[] products){
        for (String product : products){
            String[] productData = product.split("\\|");
            String key = productData[0];
            ArrayList item = makeProductFromString(productData);
            //System.out.println(item);
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

            } else if(input.equals("1")){
                System.out.println(getInventoryAsString());
            } else if (input.equals("2")){
                System.out.println("What would you like to purchase?");
                String slotLocation = scan.next();
                Product purchasedProduct = deliverItem(slotLocation);
                System.out.println("You purchased " + purchasedProduct.getName() +
                        "\n Your remaining balance is " + balance);
            }

            else System.out.println("Try again");

        }
        scan.close();

    }
    public Map<String, ArrayList<Product>> getInventory(){
        return this.inventory;
    }

    public static void main(String[] args) {
        VendingMachine vendingMachine = new VendingMachine();
        vendingMachine.start();
    }

    public void feedMoney(Double amountToAdd){
        if (amountToAdd == 1.0 || amountToAdd == 2.0 || amountToAdd == 5.0
                || amountToAdd ==10.0) {
            balance += amountToAdd;
        }else {
            System.out.println("You have entered an invalid amount");
        }
    }

    public Product deliverItem(String slotLocation){
        ArrayList<Product> products = inventory.get(slotLocation);
        Product productToBePurchased = products.remove(0);
        if(balance > productToBePurchased.getPrice()){
            balance -= productToBePurchased.getPrice();
            return productToBePurchased;
        }
        else {
            // THROW EXCEPTION HANDLE IT IN VENT METHOD
            return null;
        }

    }


    public Double giveChange(){
        Double changeToGive = balance;
        balance = 0.0;
        return changeToGive;

    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public String getInventoryAsString(){
        StringBuilder inventoryAsString = new StringBuilder();
        for(Map.Entry<String, ArrayList<Product>> products: inventory.entrySet()){
            inventoryAsString.append(products.getKey());
            inventoryAsString.append(" : ");
            inventoryAsString.append(products.getValue().get(0).toString());
            inventoryAsString.append(" : Quantity ");
            inventoryAsString.append(products.getValue().size());

            inventoryAsString.append("\n");
        }
        return inventoryAsString.toString();
    }


}
