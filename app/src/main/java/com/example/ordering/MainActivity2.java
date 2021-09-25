package com.example.ordering;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
    }

    public void goToInsert(View view)
    {
        Intent intent = new Intent(MainActivity2.this,MainActivity.class);
        startActivity(intent);
    }

    public void goToDisplay(View view)
    {
        Intent intent = new Intent(MainActivity2.this,OrderConfirmation.class);
        startActivity(intent);
    }
}