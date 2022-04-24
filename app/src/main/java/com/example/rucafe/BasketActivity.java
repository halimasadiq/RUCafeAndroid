package com.example.rucafe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.util.ArrayList;

public class BasketActivity extends AppCompatActivity {

    private static final ArrayList<String> items = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basket);
        setTitle(getResources().getText(R.string.basket));
    }

    public void addToItems(String item){
        items.add(item);
        System.out.println(items.size());
    }
    public void removeFromItems(String item){
        items.remove(item);
    }
}