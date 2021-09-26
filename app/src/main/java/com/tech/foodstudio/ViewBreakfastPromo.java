package com.tech.foodstudio;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.tech.foodstudio.Database.DatabaseHelper;

public class ViewBreakfastPromo extends AppCompatActivity {

    RecyclerView mRecyclerView;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_breakfast_promo);

        mRecyclerView = (RecyclerView) findViewById(R.id.recylceView);
        databaseHelper = new DatabaseHelper(ViewBreakfastPromo.this);

        showRercord();

    }

    public void showRercord() {

        Adapter adapter = new Adapter(ViewBreakfastPromo.this, databaseHelper.getAllData(Constants.ID + " DESC"));
        mRecyclerView.setAdapter(adapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        showRercord();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }
}