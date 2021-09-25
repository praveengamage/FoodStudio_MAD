package com.example.ordering;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class OrderConfirmation extends AppCompatActivity {

    private TextView conOrder;

    private Button button,view;
    DBHelper DB;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_confirmation);

        button = (Button)findViewById(R.id.btnOplace);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                openOrderPlacement();
            }
        });

        button = (Button)findViewById(R.id.btncancel2);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                openMainActivity();
            }
        });



    }
    public void openOrderPlacement(){
        Intent intent = new Intent(this, orderPlacement.class);
        startActivity(intent);
    }

    public void openMainActivity(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}