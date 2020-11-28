package com.codedifferently.walkthrough.vendingmachine.menu;

import java.util.ArrayList;

public class Menu {
    private ArrayList<String> options;
    //generic container; a placeholder that helps you be able to reuse code
    public Menu(ArrayList<String> optionsIn){
        this.options = optionsIn;
    }

    public ArrayList<String> getOptions(){
        return options;

    }
}
