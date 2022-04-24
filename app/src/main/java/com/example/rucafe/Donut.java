package com.example.rucafe;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * This class defines the Donut abstract data type with donut prices and flavors
 * This class extends the MenuItem abstract class
 * @author Halima Sadiq, Shajia Subhani
 */
public class Donut extends MenuItem {

    private static final double YEAST_DONUT_PRICE = 1.59;
    private static final double CAKE_DONUT_PRICE = 1.79;
    private static final double DONUT_HOLE_PRICE = 0.39;

    private static final ArrayList<String> YEAST_FLAVORS = new ArrayList<String>(Arrays.asList("jelly", "glazed", "chocolate", "custard", "coconut", "french"));
    private static final ArrayList<String> CAKE_FLAVORS = new ArrayList<String>(Arrays.asList("vanilla", "strawberry", "blueberry", "lemon", "apple"));
    private static final ArrayList<String> HOLE_FLAVORS = new ArrayList<String>(Arrays.asList("glazed", "chocolate", "powdered"));

    private String donutType;
    private String donutFlavor;
    private int number;
    private int imageID;

    /**
     * Default constructor for donut sets quantity number to 1
     */
    public Donut(){
        number = 1;
    }

    /**
     * Parameterized constructor that takes donutType
     * @param donutType - donutType
     * */
    public Donut(String donutType){
        this.donutType = donutType;
        number = 1;
    }

    /**
     * Parameterized constructor that takes quantity, donutType and donutFlavor
     * @param number - quantity
     * @param donutType - donutType
     * @param donutFlavor - donut flavor
     * */
    public Donut(int number, String donutType, String donutFlavor){
        if ((donutType.equals("yeast donuts")) ||
                (donutType.equals("cake donuts")) ||
                (donutType.equals("donut holes"))) {
            this.donutType = donutType;
            this.donutFlavor = donutFlavor;
            this.number = number;
        }
    }

    /**
     * Parameterized constructor that takes donutType and donutFlavor
     * @param donutType - donutType
     * @param donutFlavor - donutFlavor as string
     */
    public Donut(String donutType, String donutFlavor) {
        if ((donutType.equals("yeast donuts") && YEAST_FLAVORS.contains(donutFlavor.toLowerCase())) ||
                (donutType.equals("cake donuts") && CAKE_FLAVORS.contains(donutFlavor.toLowerCase())) ||
                (donutType.equals("donut holes") && HOLE_FLAVORS.contains(donutFlavor.toLowerCase()))) {
            this.donutType = donutType;
            this.donutFlavor = donutFlavor;
            number = 1;
        }
    }

    /**
     * Sets the instance donut type to the specific donut type
     * @param donutType - donut type to set to
     */
    public void setDonutType(String donutType) {
        this.donutType = donutType;
    }

    /**
     * Sets the instance image id to the specific donut image
     * @param imageID - image number
     */
    public void setImageID(int imageID) {
        this.imageID = imageID;
    }
    /**
     * Sets donut quantity to the specified value
     * @param number - quantity to set to
     */
    public void setNumber(int number){
        this.number = number;
    }

    /**
     * Gets the donut type of the instance
     * @return donutType
     */
    public String getDonutType() {
        return donutType;
    }

    /**
     * Gets the donut image of the instance
     * @return donut image
     */
    public int getImageID() {
        return imageID;
    }

    /**
     * Gets the donut flavor of the instance
     * @return donutFlavor
     */
    public String getDonutFlavor() {
        return donutFlavor;
    }
    /**
     * Gets the array list of all yeast donut flavors
     * @return yeast donut flavors
     */
    public ArrayList<String> getYeastFlavors(){
        return YEAST_FLAVORS;
    }

    /**
     * Gets the array list of all cake donut flavors
     * @return cake donut flavors
     */
    public ArrayList<String> getCakeFlavors(){
        return CAKE_FLAVORS;
    }

    /**
     * Gets the array list of all donut hole flavors
     * @return donut hole flavors
     */
    public ArrayList<String> getHoleFlavors(){
        return HOLE_FLAVORS;
    }

    /**
     * Implementation of itemPrice() from MenuItem abstract superclass
     * Calculates total item price given donut type and quantity
     * @return total item price
     */
    public double itemPrice() {
        if (donutType.equals("yeast donuts")) {
            return YEAST_DONUT_PRICE * number;
        } else if (donutType.equals("cake donuts")) {
            return CAKE_DONUT_PRICE * number;
        } else if (donutType.equals("donut holes")) {
            return DONUT_HOLE_PRICE * number;
        }
        return 0;
    }
    /**
     * Overrides the toString() method to return a textual representation of Donut
     * @return "(quantity) DonutFlavor - DonutType"
     */
    @Override
    public String toString(){
        return "(" + number + ") " + donutFlavor + " - " + donutType;
    }

    /**
     * Overrides the equals method to check if two Donut objects are equal
     * @param obj - to compare to
     * @return true if equal, else false
     */
    @Override
    public boolean equals(Object obj){
        if(obj instanceof Donut){
            Donut d = (Donut) obj;
            if(d.number == this.number && d.donutType.equals(this.donutType) && d.donutFlavor.equals(this.donutFlavor)){
                return true;
            }
        }
        return false;
    }


}
