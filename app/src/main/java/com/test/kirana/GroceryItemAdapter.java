package com.test.kirana;

import androidx.annotation.NonNull;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

public class GroceryItemAdapter extends ArrayAdapter<GroceryItem> {
    private int netCount = 0; private double netPrice;
    private boolean deleteWhenEmpty = false;

    public int getNetQuantityCount() { return netCount; }
    public double getNetPrice() { return netPrice; }

    public interface OnTotalPriceChangeListener {
        void onTotalPriceChange(int newQuantity, double newPrice);
    }

    private OnTotalPriceChangeListener totalPriceChangeListener;

    public void shouldDeleteWhenEmpty(boolean value) {
        this.deleteWhenEmpty = value;
    }

    GroceryItemAdapter(Context context) {
        super(context, R.layout.grocery_item, new ArrayList<GroceryItem>());
    }
    @NonNull @Override
    public View getView(final int position, View rootView, ViewGroup parent) {
        final GroceryItem item = getItem(position);
        @SuppressLint("ViewHolder")
        View view = LayoutInflater.from(getContext()).inflate(R.layout.grocery_item, parent, false);
        if(item!=null && view!=null) {
            final TextView name, price, quantity, netWeight;
            name = view.findViewById(R.id.name); price = view.findViewById(R.id.price);
            quantity = view.findViewById(R.id.quantity); netWeight = view.findViewById(R.id.net_weight);
            ImageButton add, subtract; add = view.findViewById(R.id.btn_add); subtract = view.findViewById(R.id.btn_sub);
            View.OnClickListener quantityListener = new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(v.getId()==R.id.btn_add) { item.setQuantity(item.getQuantity()+1); netCount++; netPrice+=item.getPrice(); }
                    else if(item.getQuantity()>0) { item.setQuantity(item.getQuantity()-1); netCount--; netPrice-=item.getPrice(); }
                    quantity.setText(String.valueOf(item.getQuantity()));
                    if(deleteWhenEmpty && item.getQuantity()==0) remove(item);
                    if(totalPriceChangeListener!=null) totalPriceChangeListener.onTotalPriceChange(netCount, netPrice);
                }
            };
            add.setOnClickListener(quantityListener); subtract.setOnClickListener(quantityListener);
            name.setText(item.getName());
            quantity.setText(String.valueOf(item.getQuantity()));
            price.setText(("₹ "+item.getPrice()));
            netWeight.setText(item.getNetWeight());
        }
        return view;
    }

    void addOnTotalPriceChangeListener(OnTotalPriceChangeListener totalPriceChangeListener) {
        this.totalPriceChangeListener = totalPriceChangeListener;
    }

    @Override public void add(GroceryItem item) {
        super.add(item);
        netCount += item.getQuantity();
        netPrice += item.getTotalPrice();
        if(totalPriceChangeListener!=null) totalPriceChangeListener.onTotalPriceChange(netCount, netPrice);
    }
}
