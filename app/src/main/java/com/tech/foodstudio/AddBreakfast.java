package com.tech.foodstudio;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.tech.foodstudio.Database.DatabaseHelper;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class AddBreakfast extends AppCompatActivity {

    TextView textView1;
    TextView textView2;
    EditText editText;
    Button button;
    Button button1;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_breakfast);

        editText = (EditText)findViewById(R.id.promocode);
        textView1 = (TextView)findViewById(R.id.date);
        textView2 = (TextView)findViewById(R.id.Description);
        button = (Button)findViewById(R.id.add);
        button1 = (Button)findViewById(R.id.viewpromo);
        dbHelper = new DatabaseHelper(AddBreakfast.this);

        Date c = Calendar.getInstance().getTime();
        System.out.println("Current time => " + c);

        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault());
        String formattedDate = df.format(c);

        textView1.setText(formattedDate);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String date = textView1.getText().toString();
                String description = textView2.getText().toString();
                String code = editText.getText().toString();

                if(code.isEmpty()){
                    editText.setError("Promo Code is required");
                }else{
                    dbHelper.insertInfo(
                            "" + date,
                            "" + description,
                            "" + code);

                    Toast.makeText(AddBreakfast.this, "Entered details successfully", Toast.LENGTH_SHORT).show();

                }

            }
        });

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddBreakfast.this, ViewBreakfastPromo.class);
                startActivity(intent);
            }
        });
    }
}