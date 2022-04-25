package com.example.rucafe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

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

    private Coffee coffee;

    private ArrayList<CoffeeAddIns> coffeeAddInsArrayList;

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
                    coffeeAddInsArrayList.add(CoffeeAddIns.CARAMEL);
                    //update subtotal
                }
            }
        });

        CB_Cream.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(CB_Cream.isChecked()){
                    coffeeAddInsArrayList.add(CoffeeAddIns.CREAM);
                    //update subtotal
                }
            }
        });

        CB_Milk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(CB_Milk.isChecked()){
                    coffeeAddInsArrayList.add(CoffeeAddIns.MILK);
                    //update subtotal
                }
            }
        });

        CB_WhippedCream.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(CB_WhippedCream.isChecked()){
                    coffeeAddInsArrayList.add(CoffeeAddIns.WHIPPED_CREAM);
                    //update subtotal
                }
            }
        });
        CB_Syrup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(CB_Syrup.isChecked()){
                    coffeeAddInsArrayList.add(CoffeeAddIns.SYRUP);
                    //update subtotal
                }
            }
        });

        //FOR TEXTVIEWS
        TV_Qty = findViewById(R.id.tv_Qty);
        TV_Size = findViewById(R.id.tv_size);
        TV_Subtotal = findViewById(R.id.tv_subtotal);
        TV_NumQty = findViewById(R.id.et_setQuantity);
        findCoffeeNumber(TV_NumQty);

        //FOR SPINNER
        SP_Size = findViewById(R.id.spinner_size);
        populateSize(SP_Size);

        //FOR BUTTON
        But_ATB = findViewById(R.id.but_addToBasket);

    }

    /**
     * This helper method will set the quantity for our coffee order
     * @param Qty is user input for how many coffees they want
     */
    private void findCoffeeNumber(EditText Qty){
        int amount = Integer.parseInt(Qty.getText().toString());
        coffee.setNumber(amount);
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
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        coffee.setSize("short");
    }
    //NEED HELP HERE CONNECTING ADDINS ARRAY LIST TO COFFEE OBJECT
    private void subtotalDisplay(TextView sub_tot){
        double subtotal = coffee.itemPrice();
    }
}