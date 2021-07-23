package com.test.kirana;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class enter_details extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    Bundle bundle;
    String[] state_array = {"Select","Delhi", "Maharastra", "Tamil Nadu", "West Bengal", "Jharkhand", "Bihar", "Karnatka"};
    Button button;
    TextView textView;
    EditText et1,et2,et3,et4;
    AlertDialog.Builder builder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_details);
        et1=(EditText)findViewById(R.id.name);
        et2=(EditText)findViewById(R.id.email);
        et3=(EditText)findViewById(R.id.phone);
        et4=(EditText)findViewById(R.id.add);
        bundle = getIntent().getExtras();
        textView=(TextView)findViewById(R.id.itemsDetail);
        double price = bundle.getDouble(Cart.PRICE, 0.0);
        int quan = bundle.getInt(Cart.QUANTITY, 0);
        textView.setText(("Total Price: ₹ "+price+" Total Items: "+quan));

        Spinner citySpinner = (Spinner) findViewById(R.id.city_spinner);
        Spinner stateSpinner = (Spinner) findViewById(R.id.state_spinner);

        citySpinner.setOnItemSelectedListener(this);

        ArrayAdapter<CharSequence> cityAdapter = ArrayAdapter.createFromResource(this,
                R.array.city_array, android.R.layout.simple_spinner_item);
        cityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        citySpinner.setAdapter(cityAdapter);

        stateSpinner.setOnItemSelectedListener(this);

        ArrayAdapter<String> stateAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, state_array);
        stateAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        stateSpinner.setAdapter(stateAdapter);

        builder=new AlertDialog.Builder(this);

        button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(et1.getText().toString().isEmpty() || et2.getText().toString().isEmpty()||et3.getText().toString().isEmpty()||et4.getText().toString().isEmpty())
                {
                    builder.setMessage("All Fields Necessary")
                            .setCancelable(false)
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {

                                }
                            })
                            .setNegativeButton("", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    //  Action for 'NO' Button
                                    dialog.cancel();
                                }
                            });
                    //Creating dialog box
                    AlertDialog alert = builder.create();
                    //Setting the title manually
                    alert.setTitle("ERROR");
                    alert.show();
                }
                else {
                    builder.setMessage("Sure To Place")
                            .setCancelable(false)
                            .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    Toast.makeText(getApplicationContext(), "Order Placed",
                                            Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                    startActivity(intent);
                                }
                            })
                            .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    //  Action for 'NO' Button
                                    dialog.cancel();
                                    Toast.makeText(getApplicationContext(), "Order Canceled",
                                            Toast.LENGTH_SHORT).show();
                                }
                            });
                    //Creating dialog box
                    AlertDialog alert = builder.create();
                    //Setting the title manually
                    alert.setTitle("CONFIRMATION");
                    alert.show();
                }
            }
        });

    }

    public void onRadioButtonClicked(View view) {
        switch (view.getId()) {
            case R.id.cod:
                Toast.makeText(this, "PAT AT DOOR", Toast.LENGTH_SHORT).show();
                break;
            case R.id.upi:
                Toast.makeText(this, "PAT ONLINE USING UPI ID", Toast.LENGTH_SHORT).show();
                break;
            case R.id.ib:
                Toast.makeText(this, "PAT ONLINE USING INTERNET BANKING", Toast.LENGTH_SHORT).show();
                break;
            case R.id.card:
                Toast.makeText(this, "PAT ONLINE USING CREDIT OR DEBIT CARD", Toast.LENGTH_SHORT).show();
                break;
        }
    }
        public void onItemSelected (AdapterView < ? > arg, View view,int pos, long id)
        {
            if(pos!=0) {
                String text = arg.getItemAtPosition(pos).toString();
                Toast.makeText(arg.getContext(), "Selected:" + text, Toast.LENGTH_SHORT).show();
            }
        }

        public void onNothingSelected (AdapterView < ? > parent){

        }
    }
