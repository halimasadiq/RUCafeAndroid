package com.example.rucafe;

/**
 * This interface defines Customizable with methods to add or remove objects
 * @author Halima Sadiq, Shajia Subhani
 */
public interface Customizable {

    /**
     * This method adds an object
     * @param obj - an instance of object
     * @return returns true if successfully added, else false
     */
    boolean add(Object obj);
    /**
     * This method removes an object
     * @param obj - an instance of object
     * @return returns true if successfully removed, else false
     */
    boolean remove(Object obj);
}
