package com.example.rucafe;

/**
 * This enum class contains all the add-ins for coffee
 * @author Shajia Subhani, Halima Sadiq
 */
public enum CoffeeAddIns {
    CREAM,
    SYRUP,
    MILK,
    CARAMEL,
    WHIPPED_CREAM;

    /**
     * Overrides toString() method and returns the add-in
     * @return add-in name
     */
    @Override
    public String toString(){
        return name();
    }

}
