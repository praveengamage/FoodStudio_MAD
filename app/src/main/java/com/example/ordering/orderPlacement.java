package com.example.ordering;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class orderPlacement extends AppCompatActivity {
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_placement);

        button = (Button)findViewById(R.id.btndelete);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                openOrderConfirmation();
            }
        });

        button = (Button)findViewById(R.id.btnpay);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                openOrderPlacement();
            }
        });
    }
    public void openOrderPlacement(){
        Intent intent = new Intent(this, orderPlacement.class);
        startActivity(intent);
    }

    public void openOrderConfirmation(){
        Intent intent = new Intent(this, OrderConfirmation.class);
        startActivity(intent);
    }
}