package com.example.rucafe;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

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

    public void cancelOrder(View view){
        ArrayList<Order> ordersI = s.getOrders();
        if(ordersI.size() == 0){
            Toast.makeText(getApplicationContext(), "No Order to Cancel", Toast.LENGTH_LONG).show();
        }
        else {
            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            alert.setTitle("Cancel Selected Order");
            alert.setMessage("Order Number " + orderNum.getSelectedItem().toString());
            alert.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    int orderNumSelected = Integer.parseInt(orderNum.getSelectedItem().toString());
                    ArrayList<Order> orders = s.getOrders();
                    for (int i = 0; i < orders.size(); i++) {
                        if (orders.get(i).getOrderNum() == orderNumSelected) {
                            s.remove(orders.get(i));
                            orderNumbers.remove(Integer.valueOf(orderNumSelected));
                            adapter.notifyDataSetChanged();
                            orders = s.getOrders();
                            orderNum.setSelection(0);
                            if (orders.size() != 0) {
                                orderDetails.setText(orders.get(0).toString());
                                String formatTotal = String.format("%.2f", orders.get(0).getTotal());
                                totalS.setText(formatTotal);
                            } else {
                                orderDetails.setText("");
                                totalS.setText("");
                            }
                        }
                    }
                    Toast.makeText(getApplicationContext(), "Order Cancelled", Toast.LENGTH_LONG).show();
                }
            }).setNegativeButton("no", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    Toast.makeText(getApplicationContext(), "Order Not Cancelled", Toast.LENGTH_LONG).show();
                }
            });
            AlertDialog dialog = alert.create();
            dialog.show();
        }
    }
}