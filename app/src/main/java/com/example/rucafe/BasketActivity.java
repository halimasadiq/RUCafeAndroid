package com.example.rucafe;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class BasketActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private static final ArrayList<String> items = new ArrayList<>();
    private static final StoreOrdersActivity storeOrders = new StoreOrdersActivity();
    private ArrayAdapter<String> adapter;
    private ListView listView;
    private static final double SALES_TAX = 0.06625;
    private static double subTotalGot = 0;
    private TextView subtotal;
    private TextView tax;
    private TextView total;
    private String selected;
    private static Order order = new Order();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basket);
        setTitle(getResources().getText(R.string.basket));
        subtotal = findViewById(R.id.subtotalB);
        tax = findViewById(R.id.tax);
        total = findViewById(R.id.totalB);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items);
        listView = findViewById(R.id.items_list);
        listView.setOnItemClickListener(this);
        listView.setAdapter(adapter);
        printAmount();
    }

    public void addToItems(String item, double additionalCost){
        subTotalGot = subTotalGot + additionalCost;

        if (item.contains("donuts") || item.contains("donut")) {
            String[] tokens = item.split("-");
            String[] donutInfo = tokens[0].split(" ");
            String numStr = donutInfo[0].replace("(","");
            numStr = numStr.replace(")","");
            Donut d = new Donut(Integer.parseInt(numStr), tokens[1].trim(), donutInfo[1].trim());
            order.add(d);
            //printAmount();
        }
        else{
            String[] tokens = item.split(" ");
            String numStr = tokens[0].replace("(","");
            numStr = numStr.replace(")","");
            String size = tokens[2];
            ArrayList<CoffeeAddIns> addIns = new ArrayList<>();
            for(int i = 3; i<tokens.length; i++){
                addIns.add(CoffeeAddIns.valueOf(tokens[i]));
            }

            Coffee c = new Coffee(Integer.parseInt(numStr),size,addIns);
            order.add(c);
        }
        items.add(item);
    }
    public void removeFromItems(String item){
        items.remove(item);
        order.remove(item);
    }

    private void printAmount(){
        String formatSub = String.format("%.2f",subTotalGot);
        subtotal.setText(formatSub);
        double taxCalc = Double.parseDouble(subtotal.getText().toString()) * SALES_TAX;
        String formatTax = String.format("%.2f",taxCalc);
        tax.setText(formatTax);
        double totalCalc = taxCalc + Double.parseDouble(subtotal.getText().toString());
        String formatTotal = String.format("%.2f",totalCalc);
        total.setText(formatTotal);

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        selected = listView.getItemAtPosition(i).toString();
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Remove Selected Item");
        alert.setMessage(adapterView.getAdapter().getItem(i).toString());
        //anonymous inner class to handle the onClick event of YES or NO.
        alert.setPositiveButton("yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                remove();
                Toast.makeText(getApplicationContext(), "Item Removed", Toast.LENGTH_LONG).show();
            }
        }).setNegativeButton("no", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(), "No Item Removed", Toast.LENGTH_LONG).show();
            }
        });
        AlertDialog dialog = alert.create();
        dialog.show();
    }

    public void remove(){
        if(selected == null){
            Toast.makeText(getApplicationContext(),
                    "No item selected", Toast.LENGTH_LONG).show();
        }
        else {
            if (selected.contains("donuts") || selected.contains("donut")) {
                String[] tokens = selected.split("-");
                String[] donutInfo = tokens[0].split(" ");
                String numStr = donutInfo[0].replace("(","");
                numStr = numStr.replace(")","");
                Donut d = new Donut(Integer.parseInt(numStr), tokens[1].trim(), donutInfo[1].trim());
                order.remove(d);
                subTotalGot = Math.abs(subTotalGot - d.itemPrice());
                printAmount();
            }
            else{
                String[] tokens = selected.split(" ");
                String numStr = tokens[0].replace("(","");
                numStr = numStr.replace(")","");
                String size = tokens[2];
                ArrayList<CoffeeAddIns> addIns = new ArrayList<>();
                for(int i = 3; i<tokens.length; i++){
                    addIns.add(CoffeeAddIns.valueOf(tokens[i]));
                }

                Coffee c = new Coffee(Integer.parseInt(numStr),size,addIns);
                order.remove(c);
                subTotalGot = Math.abs(subTotalGot - c.itemPrice());
                printAmount();
            }
            items.remove(selected);
            adapter.notifyDataSetChanged();
        }
    }

    public void placeOrder(View view){
        if(items.isEmpty()){
            Toast.makeText(getApplicationContext(),
                    "No items in basket to place order", Toast.LENGTH_LONG).show();
        }
        else{
            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            alert.setTitle("Place Order");
            alert.setMessage("Confirm to place order");
            //anonymous inner class to handle the onClick event of YES or NO.
            alert.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    order.setTotal(Double.parseDouble(total.getText().toString()));
                    storeOrders.addOrder(order);
                    storeOrders.setOrdersAdded();
                    items.clear();
                    adapter.notifyDataSetChanged();
                    subtotal.setText("0.00");
                    tax.setText("0.00");
                    total.setText("0.00");
                    subTotalGot = 0;
                    order = new Order();
                    Toast.makeText(getApplicationContext(), "Order Placed", Toast.LENGTH_LONG).show();
                }
            }).setNegativeButton("no", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    Toast.makeText(getApplicationContext(), "Order Not Placed", Toast.LENGTH_LONG).show();
                }
            });
            AlertDialog dialog = alert.create();
            dialog.show();


        }
    }
}