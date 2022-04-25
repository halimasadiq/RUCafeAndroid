package com.example.rucafe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
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
    private TextView TV_NumQty;

    private Button But_ATB;

    private Spinner SP_Size;

    private Coffee coffee;

    private ArrayList<String> addIns;

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
        addIns = new ArrayList<>();

        CB_Caramel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(CB_Caramel.isChecked()){
                    addIns.add("caramel");
                }
            }
        });

        //FOR TEXTVIEWS
        TV_Qty = findViewById(R.id.tv_Qty);
        TV_Size = findViewById(R.id.tv_size);
        TV_Subtotal = findViewById(R.id.tv_subtotal);
        TV_NumQty = findViewById(R.id.tv_setQuantity);

        //FOR SPINNER
        SP_Size = findViewById(R.id.spinner_size);
        populateSize(SP_Size);

        //FOR BUTTON
        But_ATB = findViewById(R.id.but_addToBasket);

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

    }
}