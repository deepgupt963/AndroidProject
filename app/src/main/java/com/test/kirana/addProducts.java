package com.test.kirana;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class addProducts extends AppCompatActivity {
    Context context = this;
    Grocery_DATA  grocery_data;
    TextView netQuantity, netPrice;
    ImageButton imageButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_products);
        grocery_data = new Grocery_DATA(this);
        if(Grocery_DATA.count()==0) fetchData();

        netPrice = findViewById(R.id.net_price); netQuantity = findViewById(R.id.net_quantity);

        final GroceryItemAdapter adapter = new GroceryItemAdapter(this);
        adapter.addOnTotalPriceChangeListener(new GroceryItemAdapter.OnTotalPriceChangeListener() {
            @Override
            public void onTotalPriceChange(int newQuantity, double newPrice) {
                netPrice.setText(("₹ "+newPrice)); netQuantity.setText((newQuantity+" item"+(newQuantity==1?"":"s")));
            }
        });
        Cursor cur = Grocery_DATA.fetchAll();
        for(cur.moveToFirst();!cur.isAfterLast();cur.moveToNext()) {
            GroceryItem item = new GroceryItem();
            item.setName(cur.getString(cur.getColumnIndex("ITEM_NAME"))); item.setQuantity(0);
            item.setNetWeight(cur.getString(cur.getColumnIndex("ITEM_QUANTITY")));
            item.setPrice(Double.parseDouble(cur.getString(cur.getColumnIndex("PRICE"))));
            adapter.add(item);
        }

        ListView product_list = findViewById(R.id.product_list);
        product_list.setAdapter(adapter);

        imageButton=(ImageButton)findViewById(R.id.imageButton);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    ArrayList<GroceryItem> arrayList =new ArrayList<>();
                    for(int i=0;i<adapter.getCount();i++)
                    { if(adapter.getItem(i).getQuantity()>0) { arrayList.add(adapter.getItem(i)); } }

                    Intent intent=new Intent(addProducts.this,Cart.class);
                    intent.putExtra("cart_key",arrayList);
                    startActivity(intent);
                } catch(Exception e) { Log.e("TAG", "error: "+e); e.printStackTrace(); }
            }
        });
    }

    void fetchData() {
        grocery_data = grocery_data.open();

        String P1 = "COLGATE";
        String PP1 = "50";
        String PQ1="200G";
        grocery_data.insertEntry(P1,PP1,PQ1);

        String P2 = "BATHINGSOAP";
        String PP2 = "40";
        String PQ2="150G";
        grocery_data.insertEntry(P2,PP2,PQ2);

        String P3 = "DETERGENT";
        String PP3 = "200";
        String PQ3="500G";
        grocery_data.insertEntry(P3,PP3,PQ3);

        String P4 = "BATHROOMFRESHNER";
        String PP4 = "50";
        String PQ4="10G";
        grocery_data.insertEntry(P4,PP4,PQ4);

        String P5 = "TOILETCLEANER";
        String PP5 = "150";
        String PQ5="500ML";
        grocery_data.insertEntry(P5,PP5,PQ5);

        String P6 = "BATHINGTOWEL";
        String PP6 = "200";
        String PQ6="1M";
        grocery_data.insertEntry(P6,PP6,PQ6);

        String P7 = "HANDWASH";
        String PP7 = "150";
        String PQ7="200ML";
        grocery_data.insertEntry(P7,PP7,PQ7);

        String P8 = "BUCKET";
        String PP8 = "250";
        String PQ8="20L(VOL)";
        grocery_data.insertEntry(P8,PP8,PQ8);

        String P9 = "JUG";
        String PP9 = "50";
        String PQ9="1L(VOL)";
        grocery_data.insertEntry(P9,PP9,PQ9);

        String P10 = "MASOOR DAL";
        String PP10 = "80";
        String PQ10="1KG";
        grocery_data.insertEntry(P10,PP10,PQ10);

        String P11 = "MOONG DAL";
        String PP11 = "70";
        String PQ11="1KG";
        grocery_data.insertEntry(P11,PP11,PQ11);

        String P12 = "ARHAR DAL";
        String PP12 = "120";
        String PQ12="1KG";
        grocery_data.insertEntry(P12,PP12,PQ12);

        String P13 = "CHANA DAL";
        String PP13 = "100";
        String PQ13="1KG";
        grocery_data.insertEntry(P13,PP13,PQ13);

        String P14 = "RAJMA";
        String PP14 = "80";
        String PQ14="500G";
        grocery_data.insertEntry(P14,PP14,PQ14);

        String P15 = "CHANA";
        String PP15 = "60";
        String PQ15="500G";
        grocery_data.insertEntry(P15,PP15,PQ15);

        String P16 = "TURMERIC POWER";
        String PP16 = "50";
        String PQ16="200G";
        grocery_data.insertEntry(P16,PP16,PQ16);

        String P17 = "CORIANDER POWER";
        String PP17 = "40";
        String PQ17="200G";
        grocery_data.insertEntry(P17,PP17,PQ17);

        String P18 = "GARAM MASALA POWER";
        String PP18 = "50";
        String PQ18="200G";
        grocery_data.insertEntry(P18,PP18,PQ18);

        String P19 = "CHILLI POWER";
        String PP19 = "50";
        String PQ19="200G";
        grocery_data.insertEntry(P19,PP19,PQ19);

        String P20 = "MEAT MASALA POWER";
        String PP20 = "50";
        String PQ20="200G";
        grocery_data.insertEntry(P20,PP20,PQ20);

        String P21 = "CUMIN SEEDS";
        String PP21 = "50";
        String PQ21="200G";
        grocery_data.insertEntry(P21,PP21,PQ21);

        String P22 = "CARADMOM";
        String PP22 = "50";
        String PQ22="10G";
        grocery_data.insertEntry(P22,PP22,PQ22);

        String P23 = "CINNAMOM";
        String PP23 = "50";
        String PQ23="10G";
        grocery_data.insertEntry(P23,PP23,PQ23);

        String P24 = "BAY LEAF";
        String PP24 = "50";
        String PQ24="200G";
        grocery_data.insertEntry(P24,PP24,PQ24);

        String P25 = "ATTA";
        String PP25 = "150";
        String PQ25="5KG";
        grocery_data.insertEntry(P25,PP25,PQ25);

        String P26 = "MUSTARD OIL";
        String PP26 = "120";
        String PQ26="1L";
        grocery_data.insertEntry(P26,PP26,PQ26);

        String P27 = "SOYA REFINED OIL";
        String PP27 = "110";
        String PQ27="1L";
        grocery_data.insertEntry(P27,PP27,PQ27);

        String P28 = "OLIVE OIL";
        String PP28 = "500";
        String PQ28="500ML";
        grocery_data.insertEntry(P28,PP28,PQ28);

        String P29 = "SEASEM OIL";
        String PP29 = "250";
        String PQ29="500ML";
        grocery_data.insertEntry(P29,PP29,PQ29);

        String P30 = "SUGAR";
        String PP30 = "50";
        String PQ30="1KG";
        grocery_data.insertEntry(P30,PP30,PQ30);

        String P31 = "TEA";
        String PP31 = "250";
        String PQ31="200G";
        grocery_data.insertEntry(P31,PP31,PQ31);

        String P32 = "RAISIN";
        String PP32 = "150";
        String PQ32="200G";
        grocery_data.insertEntry(P32,PP32,PQ32);

        String P33 = "ALMOND";
        String PP33 = "500";
        String PQ33="500G";
        grocery_data.insertEntry(P33,PP33,PQ33);

        String P34 = "CASHEW NUT";
        String PP34 = "400";
        String PQ34="500G";
        grocery_data.insertEntry(P34,PP34,PQ34);

        String P35 = "WAL NUT";
        String PP35 = "500";
        String PQ35="400G";
        grocery_data.insertEntry(P35,PP35,PQ35);

        String P36 = "DATE";
        String PP36 = "500";
        String PQ36="600G";
        grocery_data.insertEntry(P36,PP36,PQ36);

        String P37 = "BESAN";
        String PP37 = "50";
        String PQ37="500G";
        grocery_data.insertEntry(P37,PP37,PQ37);

        String P38 = "OATS";
        String PP38 = "150";
        String PQ38="500G";
        grocery_data.insertEntry(P38,PP38,PQ38);

        String P39 = "CORN FLAKES";
        String PP39 = "150";
        String PQ39="200G";
        grocery_data.insertEntry(P39,PP39,PQ39);

        String P40 = "SALT";
        String PP40 = "50";
        String PQ40="500G";
        grocery_data.insertEntry(P40,PP40,PQ40);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Grocery_DATA.close();
    }
}


