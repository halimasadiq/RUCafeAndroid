package com.example.rucafe;

import java.util.ArrayList;
/**
 * This class defines the StoreOrders abstract data type that contains all the store orders
 * This class implements Customizable interface
 * @author Halima Sadiq, Shajia Subhani
 */
public class StoreOrders implements Customizable{

    private ArrayList<Order> orders = new ArrayList<>();
    private ArrayList<Integer> orderNumbers = new ArrayList<>();
    private int numOrders;

    /**
     * Default constructor that sets number of orders to zero
     */
    public StoreOrders(){
        numOrders = 0;
    }

    /**
     * Parameterized constructor that takes numberOfOrders and arrayList of orders as input
     * @param numOrders - number of orders
     * @param orders - list of all orders of the store
     */
    public StoreOrders(int numOrders, ArrayList<Order> orders){
        this.numOrders = numOrders;
        this.orders = orders;
    }

    /**
     * Gets the number of orders in the store
     * @return number of orders
     */
    public int getNumOrders(){
        return this.numOrders;
    }

    /**
     * Gets the order by its order number
     * @param orderNum - order number
     * @return an instance of Order with the specified order number
     */
    public Order getOrderByNum(int orderNum){
        for(int i = 0; i< orders.size(); i++){
            if(orders.get(i).getOrderNum() == orderNum){
                return orders.get(i);
            }
        }
        return null;
    }

    /**
     * Gets all the orders in the store
     * @return arrayList of orders
     */
    public ArrayList<Order> getOrders(){
        return this.orders;
    }

    /**
     * Gets the order numbers of all the orders
     * @return arrayList of order numbers
     */
    public ArrayList<Integer> getOrderNumbers(){
        return this.orderNumbers;
    }

    /**
     * Implementation of add(Object obj) from Customizable interface
     * Adds the order to the arraylist of store orders
     * @param obj - object to add
     * @return true if successfully added, else false
     */
    public boolean add(Object obj){
        if(obj instanceof Order){
            numOrders++;
            Order o = (Order) obj;
            o.setOrderNum(numOrders);
            orderNumbers.add(o.getOrderNum());
            orders.add((Order) obj);
            return true;
        }
        return false;
    }

    /**
     * Implementation of remove(Object obj) from Customizable interface
     * Removes the order from the arraylist of store orders
     * @param obj - object to remove
     * @return true if successfully removed, else false
     */
    public boolean remove(Object obj){
        if(obj instanceof Order){
            if(orders.contains((Order) obj)) {
                orderNumbers.remove(Integer.valueOf(((Order) obj).getOrderNum()));
                orders.remove((Order) obj);
                return true;
            }
        }
        return false;
    }

    /**
     * Prints all the orders in the store
     * @return prints order number and details for each order
     */
    public String printAll(){
        String allOrders = "";
        for(int i = 0; i < orders.size(); i++){
            allOrders = allOrders + ("Order Number: " + orders.get(i).getOrderNum() + "\n");
            allOrders = allOrders +  (orders.get(i).toString());
            allOrders = allOrders + ("\nOrder Total: $" + orders.get(i).getTotal() + "\n");
            allOrders = allOrders + ("========================================================\n\n");
        }
        return allOrders;
    }

    /**
     * Overrides the toString() method to return a textual representation of store orders
     * @return order number followed by details
     */
    @Override
    public String toString(){
        String result = "";
        for(int i = 0; i < orders.size(); i++){
            result = result + "Order Number: " + orders.get(i).getOrderNum() + "\n" + orders.get(i).toString();
        }
        return result;
    }
}
