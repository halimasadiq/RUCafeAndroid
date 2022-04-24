package com.example.rucafe;

import java.util.ArrayList;
/**
 * This class defines the Coffee abstract data type with coffee sizes
 * This class extends the MenuItem abstract class and implements Customizable interface
 * @author Halima Sadiq, Shajia Subhani
 */
public class Coffee extends MenuItem implements Customizable{

    private static final double SHORT_PRICE = 1.69;
    private static final double TALL_PRICE = 2.09;
    private static final double GRANDE_PRICE = 2.49;
    private static final double VENTI_PRICE = 2.89;
    private static final double COST_PER_ADD_IN = 0.30;

    private ArrayList<CoffeeAddIns> addIns = new ArrayList<>();
    private String size;
    private int number;

    /**
     * Default constructor for coffee sets number to 1
     */
    public Coffee(){
        ;
    }

    /**
     * Parameterized constructor that takes coffee cup size as input parameter
     * @param size - coffee cup size as short,tall,grande or venti
     */
    public Coffee(String size){
        this.size = size;
    }

    /**
     * Parameterized constructor that takes coffee cup size and add-ins
     * @param size - coffee cup size as string as short,tall,grande or venti
     * @param addIns - ArrayList of CoffeeAddIns Type
     */
    public Coffee(String size, ArrayList<CoffeeAddIns> addIns){
        this.size = size.toLowerCase();
        this.addIns = addIns;
    }

    /**
     * Parameterized constructor that takes number of coffees, coffee cup size and add-ins
     * @param number - number of coffees
     * @param size - coffee cup size as string as short,tall,grande or venti
     * @param addIns - ArrayList of CoffeeAddIns Type
     */
    public Coffee(int number, String size, ArrayList<CoffeeAddIns> addIns){
        this.number = number;
        this.size = size.toLowerCase();
        this.addIns = addIns;

    }

    /**
     * Sets the coffee size to the specified size
     * @param size - short,venti,grande or tall as String
     */
    public void setSize(String size){
        this.size = size;
    }

    /**
     * Sets the number of coffees
     * @param number - number of coffees
     */
    public void setNumber(int number){
        this.number = number;
    }

    /**
     * gets the quanitites of coffee order
     * @return - quantity
     */
    public int getNumber(){
        return this.number;
    }
    /**
     * Implementation of itemPrice() from MenuItem abstract superclass
     * Calculates total item price given coffee number, coffee size and add-ins
     * @return total item price
     */
    public double itemPrice(){
        double sizePrice = 0;
        double totalPrice = 0;
        if(size.equals("short")){
            sizePrice = SHORT_PRICE;
        }
        else if(size.equals("tall")){
            sizePrice = TALL_PRICE;
        }
        else if(size.equals("grande")){
            sizePrice = GRANDE_PRICE;
        }
        else if(size.equals("venti")){
            sizePrice = VENTI_PRICE;
        }
        totalPrice = (sizePrice + (COST_PER_ADD_IN * addIns.size())) * number;

        return totalPrice;
    }

    /**
     * Implementation of add(Object obj) from Customizable interface
     * Adds the coffee add in to the arraylist of all add-ins
     * @param obj - object to add
     * @return true if successfully added, else false
     */
    public boolean add(Object obj){
        if(obj instanceof CoffeeAddIns){
            if(!addIns.contains((CoffeeAddIns) obj)) {
                addIns.add((CoffeeAddIns) obj);
                return true;
            }
        }
        return false;
    }

    /**
     * Implementation of remove(Object obj) from Customizable interface
     * Removes the coffee add in to the arraylist of all add-ins
     * @param obj - object to remove
     * @return true if successfully removed, else false
     */
    public boolean remove(Object obj){
        if(obj instanceof CoffeeAddIns){
            if(addIns.contains((CoffeeAddIns) obj)){
                addIns.remove((CoffeeAddIns) obj);
                return true;
            }
        }
        return false;
    }

    /**
     * Overrides the toString() method to return a textual representation of Coffee
     * @return "(quantity) coffee CoffeeSize Add-Ins"
     */
    @Override
    public String toString(){
        String addInsStr = "";
        for(CoffeeAddIns addIn : addIns){
            addInsStr += addIn.toString() + " ";
        }
        return "(" + this.number + ") " + "coffee " + size + " " +  addInsStr;
    }

    /**
     * Overrides the equals method to check if two Coffee objects are equal
     * @param obj - to compare to
     * @return true if equal, else false
     */
    @Override
    public boolean equals(Object obj){
        if(obj instanceof Coffee){
            Coffee c = (Coffee) obj;
            if(c.number == this.number && c.size.equals(this.size) && c.addIns.equals(this.addIns)){
                return true;
            }
        }
        return false;
    }
}
