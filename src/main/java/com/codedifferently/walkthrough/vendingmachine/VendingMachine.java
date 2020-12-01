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
    private int quantity = 5;
    private Double balance = 0.0;



    public VendingMachine() {
        this.scan = new Scanner(System.in);
        this.inventory = new HashMap<>();
        this.init();
    }

    public void init() {
        try {
            String output = readRawDataToString();
            String[] products = output.split("\n");
            stockVendingMachine(products);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private String readRawDataToString() throws Exception {
        ClassLoader classLoader = getClass().getClassLoader();
        String result = IOUtils.toString(classLoader.getResourceAsStream("inventory.txt"));
        return result;

    }
    private ArrayList<Product> makeProductFromString(String[] productData) {
        Product product = null;
        String type = productData[3];
        type = type.trim();
        //System.out.println("Type: " + type);
        String name = productData[1];
        Double price = Double.valueOf(productData[2]);
        ArrayList<Product> listOfProduct = new ArrayList<>();
        switch (type) {
            case "Candy":
                for (int i = 0; i < quantity; i++) {
                    listOfProduct.add(new Candy(name, price));
                }

                break;
            case "Drinks":
                for (int i = 0; i < quantity; i++) {
                    listOfProduct.add(new Drinks(name, price));
                }
                break;
            case "Chips":
                for (int i = 0; i < quantity; i++) {
                    listOfProduct.add(new Chips(name, price));
                }
                break;
            case "Gum":
                for (int i = 0; i < quantity; i++) {
                    listOfProduct.add(new Gum(name, price));
                }
                break;
            default:
                break;

        }
        return listOfProduct;
    }

    public void stockVendingMachine(String[] products) {
        for (String product : products) {
            String[] productData = product.split("\\|");
            String key = productData[0];
            ArrayList item = makeProductFromString(productData);
            //System.out.println(item);
            inventory.put(key, item);
        }
    }

    public Map<String, ArrayList<Product>> getInventory() {
        return this.inventory;
    }

    public String getInventoryAsString() {
        StringBuilder inventoryAsString = new StringBuilder();
        for (Map.Entry<String, ArrayList<Product>> products : inventory.entrySet()) {
            inventoryAsString.append(products.getKey());
            inventoryAsString.append(" : ");
            inventoryAsString.append(products.getValue().get(0).toString());
            inventoryAsString.append(" : Quantity ");
            inventoryAsString.append(products.getValue().size());

            inventoryAsString.append("\n");
        }
        return inventoryAsString.toString();
    }

    public static void main(String[] args) {
        VendingMachine vendingMachine = new VendingMachine();
        vendingMachine.start();
    }

    public ArrayList<String> getOptions(){
        ArrayList<String> options = new ArrayList<>();
        options.add("(1) Display Vending Machine Items");
        options.add("(2) Purchase");
        options.add("(3) Exit");
        return options;
    }

    public void start() {
        init();
        System.out.println("Welcome to the Vending Machine");
        boolean flag = true;
        Menu menu = new Menu(getOptions());
        while (flag) {
            for (String option : menu.getOptions()) {
                System.out.println(option);
            }
            String input = scan.next();

            if (input.equals("3")) {
                flag = false;
                System.out.println("Goodbye");

            } else if (input.equals("1")) {
                System.out.println(getInventoryAsString());
            } else if (input.equals("2")) {
                purchase();
            } else System.out.println("Try again");

        }


    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public Double getBalance() {
        return balance;
    }



    public ArrayList<String> getPurchaseOptions(){
        ArrayList<String> purchaseOptions = new ArrayList<>();
        purchaseOptions.add("(1) Feed Money");
        purchaseOptions.add("(2) Select Product");
        purchaseOptions.add("(3) Finish Transaction");
        return purchaseOptions;
    }


    public Double purchase() {
        boolean flag = true;
        Menu menu = new Menu(getPurchaseOptions());
        while (flag) {
            for (String purchaseOption : menu.getOptions()) {
                System.out.println(purchaseOption);
            }
            String purchaseInput = scan.next();

            if (purchaseInput.equals("3")) {
                flag = false;
                System.out.println("Goodbye");
            } else if (purchaseInput.equals("1")) {
                System.out.println("Enter your money: 1 or 2 or 5 or 10");
                int amountToAdd = scan.nextInt();
                this.feedMoney(amountToAdd);
            } else if (purchaseInput.equals("2")) {
                System.out.println("What would you like to purchase?  Enter the Slot Location");
                String slotLocation = scan.next().toUpperCase();
               try {
                    Product purchasedProduct = deliverItem(slotLocation);
                    System.out.println("You purchased " + purchasedProduct.getName()  +
                            "\n Your remaining balance is " + balance);
                } catch (Exception e){
                    System.out.println(ANSI_RED + "You have entered an invalid Slot Location.  Try Again." + ANSI_RESET);
                    System.out.println(getInventoryAsString());
                }

            }
        }

            return giveChange();
    }

    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_RESET = "\u001B[0m";

    public void feedMoney(int amountToAdd) {
        if (amountToAdd == 1.0 || amountToAdd == 2.0 || amountToAdd == 5.0 || amountToAdd == 10.0) {
            balance += amountToAdd;
            System.out.println("You have currently provided " + balance);
        } else {
            System.out.println(ANSI_RED + "You have entered an invalid amount.  Try Again." + ANSI_RESET);
        }
    }

    public Product deliverItem(String slotLocation) {
        ArrayList<Product> products = inventory.get(slotLocation);
        Product productToBePurchased = products.size() > 0 ? products.remove(0) : null;

        if (productToBePurchased != null && balance > productToBePurchased.getPrice()) {
            balance -= productToBePurchased.getPrice();
            return productToBePurchased;
        } else if(productToBePurchased == null) {
        throw new IllegalArgumentException(ANSI_RED + "THIS ITEM IS SOLD OUT"+ ANSI_RESET);

        }else {
             throw new IllegalArgumentException(ANSI_RED+ "You don't have enough money for that"+ ANSI_RESET);

        }

    }

    public Double giveChange() {
        Double changeToGive = balance;
        balance = 0.0;
        System.out.println(("Here is your change: "+ changeToGive));
        return changeToGive;

    }
}