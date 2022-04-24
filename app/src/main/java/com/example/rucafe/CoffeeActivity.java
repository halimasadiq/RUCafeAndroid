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

    private Button But_ATB;

    private Spinner SP_Size;

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

        //FOR SPINNER
        SP_Size = findViewById(R.id.spinner_size);
        populateSize(SP_Size);
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

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}