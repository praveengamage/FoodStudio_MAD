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

    Cursor results;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_confirmation);

        //Call the read database method
        DBHelper dbHelper = new DBHelper(this);

        //display
        results = dbHelper.getAllOrders();

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

    public void readNext(View view){
        //Create references
        TextView displayOrderDetails = (TextView)findViewById(R.id.orderDetails);
        TextView displayCustomerDetails = (TextView)findViewById(R.id.userdetails);


        if(results.moveToNext()){
            displayOrderDetails.setText(results.getString(1));
            displayCustomerDetails.setText(results.getString(2));
        }
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