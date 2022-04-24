package com.example.rucafe;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

/**
 * This is an Adapter class to be used to instantiate an adapter for the RecyclerView.
 * Must extend RecyclerView.Adapter, which will enforce you to implement 3 methods:
 *      1. onCreateViewHolder, 2. onBindViewHolder, and 3. getItemCount
 *
 * You must use the data type <thisClassName.yourHolderName>, in this example
 * <ItemAdapter.ItemHolder>. This will enforce you to define a constructor for the
 * ItemAdapter and an inner class ItemsHolder (a static class)
 * The ItemsHolder class must extend RecyclerView.ViewHolder. In the constructor of this class,
 * you do something similar to the onCreate() method in an Activity.
 * @author
 */
class DonutsAdapter extends RecyclerView.Adapter<DonutsAdapter.ItemsHolder>{

    private static final BasketActivity myBasket = new BasketActivity();
    private Context context;
    private ArrayList<Donut> items;

    public DonutsAdapter(Context context, ArrayList<Donut> items) {
        this.context = context;
        this.items = items;
    }

    /**
     * This method will inflate the row layout for the items in the RecyclerView
     * @param parent
     * @param viewType
     * @return
     */
    @NonNull
    @Override
    public ItemsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.row_view, parent, false);
        return new ItemsHolder(view);
    }

    /**
     * Assign data values for each row according to their "position" (index) when the item becomes
     * visible on the screen.
     * @param holder the instance of ItemsHolder
     * @param position the index of the item in the list of items
     */
    @Override
    public void onBindViewHolder(@NonNull ItemsHolder holder, int position) {
        //assign values for each row
        String flavor = items.get(position).getDonutFlavor() + " " +  items.get(position).getDonutType();
        holder.donut_name.setText(flavor);
        holder.im_item.setImageResource(items.get(position).getImageID());

    }

    /**
     * Get the number of items in the ArrayList.
     * @return the number of items in the list.
     */
    @Override
    public int getItemCount() {
        return items.size(); //number of MenuItem in the array list.
    }

    /**
     * Get the views from the row layout file, similar to the onCreate() method.
     */
    public static class ItemsHolder extends RecyclerView.ViewHolder {
        private static final DonutActivity donutActivity = new DonutActivity();
        private ArrayList<Donut> donutsAdded = new ArrayList<>();
        private TextView donut_name;
        private ImageView im_item;
        private TextView donut_number;
        private Button btn_remove;
        private Button btn_add;
        private ConstraintLayout parentLayout; //this is the row layout


        public ItemsHolder(@NonNull final View itemView) {
            super(itemView);

            donut_name = itemView.findViewById(R.id.donut_flavor);
            im_item = itemView.findViewById(R.id.im_item);
            btn_add = itemView.findViewById(R.id.add);
            btn_remove = itemView.findViewById(R.id.remove);
            donut_number = itemView.findViewById(R.id.numberOfDonuts);
            parentLayout = itemView.findViewById(R.id.rowLayout);
            setAddButtonOnClick(itemView);
            setRemoveButtonOnClick(itemView);
        }

        /**
         * Set the onClickListener for the button on each row.
         * Clicking on the button will create an AlertDialog with the options of YES/NO.
         * @param itemView
         */
        private void setAddButtonOnClick(@NonNull final View itemView) {
            btn_add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (donut_number.getText().toString().length() == 0) {
                        Toast.makeText(itemView.getContext(),
                                "No quantity selected", Toast.LENGTH_LONG).show();
                    } else {
                        String[] tokens = donut_name.getText().toString().split(" ");
                        String flavor = tokens[0];
                        String type = tokens[1] + " " + tokens[2];
                        int num = Integer.parseInt(donut_number.getText().toString());
                        Donut d = new Donut(num,type.trim(),flavor.trim());
                        d.setNumber(num);
                        AlertDialog.Builder alert = new AlertDialog.Builder(itemView.getContext());
                        alert.setTitle("Add to order");
                        alert.setMessage(donut_number.getText().toString() + " " + donut_name.getText().toString());
                        //handle the "YES" click
                        alert.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(itemView.getContext(),
                                        donut_number.getText().toString() + " " +
                                                donut_name.getText().toString() + " added.", Toast.LENGTH_LONG).show();
                                donutActivity.setSubtotal(d.itemPrice());
                                myBasket.addToItems(d.toString());
                                donutsAdded.add(d);
                                donut_number.setText(null);
                            }
                            //handle the "NO" click
                        });
                        alert.setNegativeButton("no", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(itemView.getContext(),
                                        donut_name.getText().toString() + " not added.", Toast.LENGTH_LONG).show();
                            }
                        });
                        AlertDialog dialog = alert.create();
                        dialog.show();
                    }
                }
            });


        }

        /**
         * Set the onClickListener for the button on each row.
         * Clicking on the button will create an AlertDialog with the options of YES/NO.
         * @param itemView
         */
        private void setRemoveButtonOnClick(@NonNull final View itemView) {
            btn_remove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (donut_number.getText().toString().length() == 0) {
                        Toast.makeText(itemView.getContext(),
                                "No quantity selected", Toast.LENGTH_LONG).show();
                    } else {
                        String[] tokens = donut_name.getText().toString().split(" ");
                        String flavor = tokens[0];
                        String type = tokens[1] + " " + tokens[2];
                        int num = Integer.parseInt(donut_number.getText().toString());
                        Donut d = new Donut(num, type.trim(), flavor.trim());
                        d.setNumber(num);
                        if (!donutsAdded.contains(d)) {
                            Toast.makeText(itemView.getContext(),
                                    donut_number.getText().toString() + " " + donut_name.getText().toString() +
                                            " not previously added Or has different number of donuts if added.", Toast.LENGTH_LONG).show();
                        } else {
                            AlertDialog.Builder alert = new AlertDialog.Builder(itemView.getContext());
                            alert.setTitle("Remove from order");
                            alert.setMessage(donut_number.getText().toString() + " " + donut_name.getText().toString());
                            //handle the "YES" click
                            alert.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    Toast.makeText(itemView.getContext(),
                                            donut_number.getText().toString() + " " +
                                                    donut_name.getText().toString() + " removed.", Toast.LENGTH_LONG).show();
                                    donutActivity.removeSubtotal(d.itemPrice());
                                    donutsAdded.remove(d);
                                    myBasket.removeFromItems(d.toString());
                                    donut_number.setText(null);
                                }
                                //handle the "NO" click
                            });
                            alert.setNegativeButton("no", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    Toast.makeText(itemView.getContext(),
                                            donut_name.getText().toString() + " not added.", Toast.LENGTH_LONG).show();
                                }
                            });
                            AlertDialog dialog = alert.create();
                            dialog.show();
                        }
                    }
                }
            });


        }

    }


}
