package com.example.ordering;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

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

        button = (Button)findViewById(R.id.btnOConfirm);
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
        });

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

    public void openOrderConfirmation(){
        Intent intent = new Intent(this, OrderConfirmation.class);
        startActivity(intent);
    }
    public void openMainActivity(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }


}