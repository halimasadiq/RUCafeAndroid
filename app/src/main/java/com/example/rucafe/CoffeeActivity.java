package com.example.rucafe;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * This class handles all the events from the Graphical User Interface in the Coffee window
 * @author Halima Sadiq, Shajia Subhani
 */
public class CoffeeActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    //Our variables to represent what we have in the xml
    private CheckBox CB_Milk;
    private CheckBox CB_WhippedCream;
    private CheckBox CB_Syrup;
    private CheckBox CB_Cream;
    private CheckBox CB_Caramel;

    private TextView TV_Subtotal;
    private TextView TV_Size;
    private TextView TV_Qty;
    private EditText TV_NumQty;

    private Button But_ATB;

    private Spinner SP_Size;

    private Coffee coffee = new Coffee();

    private ArrayList<CoffeeAddIns> coffeeAddInsArrayList;
    private static final BasketActivity basket = new BasketActivity();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coffee);
        setTitle(getResources().getText(R.string.coffee));
        //FOR CHECK BOXES
        CB_Caramel = findViewById(R.id.checkBox_Caramel);
        CB_Cream= findViewById(R.id.checkBox_cream);
        CB_Milk = findViewById(R.id.checkBox_Milk);
        CB_WhippedCream = findViewById(R.id.checkBox_WC);
        CB_Syrup = findViewById(R.id.checkBox_syrup);
        coffeeAddInsArrayList = new ArrayList<>();

        //Add any add-ins to list if there are any
        CB_Caramel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(CB_Caramel.isChecked()){
                    coffee.add(CoffeeAddIns.CARAMEL);
                }
                if(!CB_Caramel.isChecked()){
                    coffee.remove(CoffeeAddIns.CARAMEL);
                }
                subtotalDisplay();
            }
        });

        CB_Cream.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(CB_Cream.isChecked()){
                    coffee.add(CoffeeAddIns.CREAM);
                }
                if(!CB_Cream.isChecked()){
                    coffee.remove(CoffeeAddIns.CREAM);
                }
                subtotalDisplay();
            }
        });

        CB_Milk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(CB_Milk.isChecked()){
                    coffee.add(CoffeeAddIns.MILK);
                }
                if(!CB_Milk.isChecked()){
                    coffee.remove(CoffeeAddIns.MILK);
                }
                subtotalDisplay();
            }
        });

        CB_WhippedCream.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(CB_WhippedCream.isChecked()){
                    coffee.add(CoffeeAddIns.WHIPPED_CREAM);
                }
                if(!CB_WhippedCream.isChecked()){
                    coffee.remove(CoffeeAddIns.WHIPPED_CREAM);
                }
                subtotalDisplay();
            }
        });
        CB_Syrup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(CB_Syrup.isChecked()){
                    coffee.add(CoffeeAddIns.SYRUP);
                }
                if(!CB_Syrup.isChecked()){
                    coffee.remove(CoffeeAddIns.SYRUP);
                }
                subtotalDisplay();
            }
        });

        //FOR TEXTVIEWS
        TV_Qty = findViewById(R.id.tv_Qty);
        TV_Size = findViewById(R.id.tv_size);
        TV_Subtotal = findViewById(R.id.tv_subtotal);
        TV_NumQty = findViewById(R.id.et_setQuantity);

        TV_NumQty.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                subtotalDisplay();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        findCoffeeNumber();

        //FOR SPINNER
        SP_Size = findViewById(R.id.spinner_size);
        populateSize(SP_Size);

        //FOR BUTTON
        But_ATB = findViewById(R.id.but_addToBasket);

    }

    public void addToBasket(View view){
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Add to order");
        alert.setMessage(coffee.toString());
        //handle the "YES" click
        alert.setPositiveButton("yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(view.getContext(),
                        coffee.toString() + " added.", Toast.LENGTH_LONG).show();
                basket.addToItems(coffee.toString(),coffee.itemPrice());
            }
            //handle the "NO" click
        });
        alert.setNegativeButton("no", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(view.getContext(),
                        coffee.toString() + " not added.", Toast.LENGTH_LONG).show();
            }
        });
        AlertDialog dialog = alert.create();
        dialog.show();
    }

    /**
     * This helper method will set the quantity for our coffee order
     */
    private void findCoffeeNumber(){
        if(TV_NumQty.getText().toString().isEmpty()){
            coffee.setNumber(1);
        }
        else {
            int amount = Integer.parseInt(TV_NumQty.getText().toString());
            coffee.setNumber(amount);
        }
    }

    /**
     * This helper method will populate the spinner in the coffee page
     * with the 4 sizes of coffee
     * @param spinner Size spinner to be set up
     */
    private void populateSize(Spinner spinner){
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.Size,
                android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
    }

    /**
     * This method will allow us to choose a size for the coffee
     * @param adapterView from our recycler to pick a size
     * @param view
     * @param i
     * @param l
     */

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String text = adapterView.getItemAtPosition(i).toString();
        if(text.equals("short")){
            coffee.setSize("short");
        }else if(text.equals("tall")){
            coffee.setSize("tall");
        }else if(text.equals("venti")){
            coffee.setSize("venti");
        }else if(text.equals("grande")){
            coffee.setSize("grande");
        }
        subtotalDisplay();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        coffee.setSize("short");
    }
    //NEED HELP HERE CONNECTING ADDINS ARRAY LIST TO COFFEE OBJECT
    private void subtotalDisplay(){
        findCoffeeNumber();
        double subtotal = coffee.itemPrice();
        String format = String.format("%.2f",subtotal);
        TV_Subtotal.setText(format);
    }
}