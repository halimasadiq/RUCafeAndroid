package com.example.rucafe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void openDonuts(View view){
        Intent intent = new Intent(MainActivity.this, DonutActivity.class);
        startActivity(intent);
    }

    public void openCoffee(View view){
        Intent intent = new Intent(MainActivity.this, CoffeeActivity.class);
        startActivity(intent);
    }

    public void openCart(View view){
        Intent intent = new Intent(MainActivity.this, BasketActivity.class);
        startActivity(intent);
    }

    public void openOrders(View view){
        Intent intent = new Intent(MainActivity.this, StoreOrdersActivity.class);
        startActivity(intent);
    }
}
