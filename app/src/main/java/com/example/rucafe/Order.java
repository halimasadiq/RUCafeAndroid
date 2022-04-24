package com.example.rucafe;

import java.util.ArrayList;
/**
 * This class defines the Order abstract data type that stores order number, donut orders and coffee orders
 * This class implements Customizable interface
 * @author Halima Sadiq, Shajia Subhani
 */
public class Order implements Customizable{

    private ArrayList<Donut> donuts = new ArrayList<>();
    private ArrayList<Coffee> coffees = new ArrayList<>();
    private int orderNum = 0;
    private double total;
    private boolean cancelled;

    /**
     * Parameterized constructor that takes order number as input parameter
     * @param orderNum
     */
    public Order(int orderNum){
        this.orderNum = orderNum;
    }

    /**
     * Default constructor
     */
    public Order(){
        ;
    }

    /**
     * Parameterized constructor that takes order number, donut orders, coffee orders as input parameters
     * @param donuts - arrayList of donuts in the order
     * @param coffees - arrayList of coffees in the order
     * @param total - order total
     */
    public Order(ArrayList<Donut> donuts, ArrayList<Coffee> coffees, double total){
        orderNum++;
        this.donuts = donuts;
        this.coffees = coffees;
        this.total = total;
    }

    /**
     * Returns order total
     * @return - order total
     */
    public double getTotal(){
        return this.total;
    }

    /**
     * Sets order total to specified amount
     * @param total amount to update to
     */
    public void setTotal(double total){
        this.total = total;
    }
    /**
     * Sets order number to the specific order number
     * @param orderNum - order number to set to
     */
    public void setOrderNum(int orderNum){
        this.orderNum = orderNum;
    }

    /**
     * Gets the order number
     * @return the order number
     */
    public int getOrderNum(){
        return this.orderNum;
    }

    /**
     * Gets the coffees in the order
     * @return coffees
     */
    public ArrayList<Coffee> getCoffees(){
        return coffees;
    }

    /**
     * Set the coffees in the order
     * @return coffees
     */
    public void setCoffees(ArrayList<Coffee> coffees){
        this.coffees =coffees;
    }
    /**
     * Implementation of add(Object obj) from Customizable interface
     * Adds the donut or coffee order to the arraylist of appropriate arraylist of orders
     * @param obj - object to add
     * @return true if successfully added, else false
     */
    public boolean add(Object obj){
        if(obj instanceof Donut){
            return donuts.add((Donut) obj);
        }
        else if(obj instanceof Coffee){
            return coffees.add((Coffee) obj);
        }
        return false;
    }

    /**
     * Implementation of remove(Object obj) from Customizable interface
     * Removes the donut or coffee order from the arraylist of appropriate arraylist of orders
     * @param obj - object to remove
     * @return true if successfully removed, else false
     */
    public boolean remove(Object obj){
        if(obj instanceof Donut){
            if(donuts.contains((Donut) obj)) {
                donuts.remove((Donut) obj);
                return true;
            }
        }
        else if(obj instanceof Coffee){
            if(coffees.contains((Coffee) obj)) {
                return coffees.remove((Coffee) obj);
            }
        }
        return false;
    }

    /**
     * Overrides the toString() method to return a textual representation of order
     * @return donuts followed by coffees in the order
     */
    @Override
    public String toString(){
        String result = "";
        for(int i = 0; i < donuts.size(); i++){
            result = result + donuts.get(i).toString() + "\n";
        }
        for(int i = 0; i < coffees.size(); i++){
            result = result + coffees.get(i).toString() + "\n";
        }
        if(cancelled){
            result = result + "CANCELLED";
        }
        return result;
    }
}
