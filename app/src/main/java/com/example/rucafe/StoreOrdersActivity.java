package com.example.rucafe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class StoreOrdersActivity extends AppCompatActivity implements  AdapterView.OnItemSelectedListener{


    private static final StoreOrders s = new StoreOrders();
    private ArrayList<Integer> orderNumbers;
    private int ordersAdded = 0;
    private TextView orderDetails;
    private Spinner orderNum;
    private ArrayAdapter<Integer> adapter;
    private TextView totalS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_orders);
        setTitle(getResources().getText(R.string.orders));
        orderDetails = findViewById(R.id.order_details);
        orderNum = findViewById(R.id.order_nums);
        totalS = findViewById(R.id.totalOrders);
        orderNumbers = s.getOrderNumbers();
        adapter = new ArrayAdapter<Integer>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, orderNumbers);
        orderNum.setAdapter(adapter);
        orderNum.setOnItemSelectedListener(this);

        ArrayList<Order> orders = s.getOrders();
        if(orders.size() != 0){
            orderDetails.setText(orders.get(0).toString());
            String formatTotal = String.format("%.2f", orders.get(0).getTotal());
            totalS.setText(formatTotal);
        }



    }



    /**
     * Adds the given order to the store orders database
     * @param o - an instance of Order
     */
    public void addOrder(Order o){
        s.add(o);
    }

    /**
     * Updates the number of orders added by 1
     */
    public void setOrdersAdded(){
        ordersAdded++;
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        ArrayList<Order> orders = s.getOrders();
        int orderNumSelected = (int) orderNum.getItemAtPosition(i);
        for (int x = 0; x < orders.size(); x++) {
            if(orders.get(x).getOrderNum() == orderNumSelected){
                String formatTotal = String.format("%.2f", orders.get(x).getTotal());
                totalS.setText(formatTotal);
                orderDetails.setText(orders.get(x).toString());
            }
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}