package com.example.ordering;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.miqbal.ordering.Order;

public class MainActivity extends AppCompatActivity {

    TextView value;
    int count= 0;
    private Button button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        value = (TextView) findViewById(R.id.value);

        Spinner myspinner = (Spinner) findViewById(R.id.dropdown);

        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(MainActivity.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.Select));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        myspinner.setAdapter(myAdapter);

        /*button = (Button)findViewById(R.id.btnOConfirm);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                openOrderConfirmation();
            }
        });

        button = (Button)findViewById(R.id.btncancel);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                openMainActivity();
            }
        });*/

    }

    public void insertDb(View insertDB)
    {
        //create the references
        TextView value = (TextView)findViewById(R.id.value);
        EditText name = (EditText)findViewById(R.id.inputname);
        EditText contact = (EditText)findViewById(R.id.editTextPhone);
        EditText address = (EditText)findViewById(R.id.editTextTextPostalAddress);
        Spinner payment = (Spinner)findViewById(R.id.dropdown);

        //Instantiate the order object
        Order orderObj = new Order(value.getText().toString(),name.getText().toString(),contact.getText().toString(),address.getText().toString());

        //call the DBhelper's add method
        DBHelper dbHelper = new DBHelper(this);
        dbHelper.addOrder(orderObj);

        Toast.makeText(this,"Order Inserted Successfully",Toast.LENGTH_SHORT).show();

    }

    public void increment(View v){
        count++;
        value.setText("" + count);
    }
    public void decrement(View v){
        if (count <= 0) count=0;
        count--;
        value.setText(""+count);
    }

    /*public void openOrderConfirmation(){
        Intent intent = new Intent(this, OrderConfirmation.class);
        startActivity(intent);
    }
    public void openMainActivity(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }*/


}