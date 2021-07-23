package com.test.kirana;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Cart extends AppCompatActivity {
    Context context = this;
    TextView netQuantity, netPrice;
    Button button;
    AlertDialog.Builder builder;
    GroceryItemAdapter adapter;

    public static final String QUANTITY = "quantity_key", PRICE = "price_key";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        netPrice=(TextView)findViewById(R.id.cart_net_price);
        netQuantity=(TextView)findViewById(R.id.cart_net_quantity);
        button=(Button)findViewById(R.id.cart_bt1);
        builder=new AlertDialog.Builder(this);
        Bundle bundle=getIntent().getExtras();
        ArrayList<GroceryItem> arrayList= (ArrayList<GroceryItem>) bundle.get("cart_key");

        adapter = new GroceryItemAdapter(this);
        adapter.shouldDeleteWhenEmpty(true);
        adapter.addOnTotalPriceChangeListener(new GroceryItemAdapter.OnTotalPriceChangeListener() {
            @Override
            public void onTotalPriceChange(int newQuantity, double newPrice) {
                netPrice.setText(("₹ "+newPrice)); netQuantity.setText((newQuantity+" item"+(newQuantity==1?"":"s")));
            }
        });

        for(int i=0;i<arrayList.size();i++)
        {
            adapter.add(arrayList.get(i));
        }

        ListView product_list = findViewById(R.id.cart_list);
        product_list.setAdapter(adapter);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(adapter.getNetQuantityCount()>0) {
                    Intent intent=new Intent(Cart.this,enter_details.class);
                    intent.putExtra(Cart.PRICE,adapter.getNetPrice());
                    intent.putExtra(Cart.QUANTITY,adapter.getNetQuantityCount());
                    startActivity(intent);
                } else Toast.makeText(getApplicationContext(), "Cart is empty! Add items from the previous page.", Toast.LENGTH_LONG).show();
            }
        });


    }
}
