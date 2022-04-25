package com.example.rucafe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
/**
 * Main Activity to navigate to donut, coffee, basket and store orders windows
 * @author Halima Sadiq, Shajia Subhani
 */
public class MainActivity extends AppCompatActivity {

    /**
     * Initializes the main activity window and sets the title to 'Rutgers Cafe'
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle(getResources().getText(R.string.welcome));
    }

    /**
     * Handles the click of the donut image to open the donut activity
     * @param view
     */
    public void openDonuts(View view){
        Intent intent = new Intent(MainActivity.this, DonutActivity.class);
        startActivity(intent);
    }
    /**
     * Handles the click of the coffee image to open the coffee activity
     * @param view
     */
    public void openCoffee(View view){
        Intent intent = new Intent(MainActivity.this, CoffeeActivity.class);
        startActivity(intent);
    }
    /**
     * Handles the click of the basket image to open the basket activity
     * @param view
     */
    public void openCart(View view){
        Intent intent = new Intent(MainActivity.this, BasketActivity.class);
        startActivity(intent);
    }
    /**
     * Handles the click of the orders image to open the store orders activity
     * @param view
     */
    public void openOrders(View view){
        Intent intent = new Intent(MainActivity.this, StoreOrdersActivity.class);
        startActivity(intent);
    }
}
