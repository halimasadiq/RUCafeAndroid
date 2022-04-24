package com.example.rucafe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class DonutActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    private ArrayList<Donut> donuts = new ArrayList<>();

    private int[] donutHoleImages = {R.drawable.glazedholes, R.drawable.chocolateholes, R.drawable.powderedholes,
            R.drawable.jelly, R.drawable.glazed, R.drawable.chocolatenew, R.drawable.boston, R.drawable.coconut, R.drawable.frenchcruller,
            R.drawable.vanilla, R.drawable.strawberry, R.drawable.blueberry, R.drawable.lemon, R.drawable.apple};

    private Spinner spinner;
    private ArrayAdapter<String> adapter;
    private String [] types = {"yeast donuts","cake donuts", "donut holes"};
    private static TextView subtotal;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donut);
        RecyclerView rcview = findViewById(R.id.rcView_menu);
        subtotal = findViewById(R.id.subtotal);
        setupMenuItems(); //add the list of items to the ArrayList
        for(int i = 0; i< donuts.size(); i++){
            donuts.get(i).setImageID(donutHoleImages[i]);
        }
        DonutsAdapter donutsAdapter = new DonutsAdapter(this, donuts);
        rcview.setAdapter(donutsAdapter);

        rcview.setLayoutManager(new LinearLayoutManager(this));
    }

    /**
     * Helper method to set up the data (the Model of the MVC).
     */
    private void setupMenuItems() {
        Donut d = new Donut();
        ArrayList<String> holeFlavors = d.getHoleFlavors();
        for (int i = 0; i < holeFlavors.size(); i++) {
            donuts.add(new Donut("donut holes", holeFlavors.get(i)));
        }
        holeFlavors.removeAll(d.getHoleFlavors());
        holeFlavors.addAll(d.getYeastFlavors());
        for (int i = 0; i < holeFlavors.size(); i++) {
            donuts.add(new Donut("yeast donuts", holeFlavors.get(i)));
        }
        holeFlavors.removeAll(d.getYeastFlavors());
        holeFlavors.addAll(d.getCakeFlavors());
        for (int i = 0; i < holeFlavors.size(); i++) {
            donuts.add(new Donut("cake donuts", holeFlavors.get(i)));
        }

    }

    public void setSubtotal(double toAdd){
        String format = String.format("%.2f",toAdd);
        if(subtotal.getText().length() == 0){
            subtotal.setText(format);
        }
        else{
            double prev = Double.parseDouble(subtotal.getText().toString());
            double newNum = prev + toAdd;
            format = String.format("%.2f", newNum);
            subtotal.setText(format);
        }
    }

    public void removeSubtotal(double toRemove){

        double prev = Double.parseDouble(subtotal.getText().toString());
        double newNum = prev - toRemove;
        String format = String.format("%.2f", newNum);
        subtotal.setText(format);

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}