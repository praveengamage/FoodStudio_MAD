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

public class EditBreakfastPromo extends AppCompatActivity {

    TextView textView1;
    TextView textView2;
    EditText editText;
    Button button;
    Button button1;
    private DatabaseHelper dbHelper;
    String date;
    String description;
    String code;
    String id;

    private boolean editMode = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_breakfast_promo);

        editText = (EditText)findViewById(R.id.updatepromocode);
        textView1 = (TextView)findViewById(R.id.updatedate);
        textView2 = (TextView)findViewById(R.id.updateDescription);
        button = (Button)findViewById(R.id.updateadd);
        button1 = (Button)findViewById(R.id.gohome);
        dbHelper = new DatabaseHelper(EditBreakfastPromo.this);

        Intent intent = getIntent();
        editMode = intent.getBooleanExtra("EditMode", editMode);
        id = intent.getStringExtra("ID");
        date = intent.getStringExtra("date");
        description = intent.getStringExtra("description");
        code = intent.getStringExtra("code");


        if(editMode){
            editMode = intent.getBooleanExtra("EditMode", editMode);
            date = intent.getStringExtra("date");
            description = intent.getStringExtra("description");
            code = intent.getStringExtra("code");

            textView1.setText(date);
            textView2.setText(description);
            editText.setText(code);

            button1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(EditBreakfastPromo.this, CusomerHome.class));
                }
            });

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    code = "" + editText.getText().toString().trim();

                    if(editMode){

                        if(code.isEmpty()){
                            editText.setError("PromoCode is required");
                        }else {

                            dbHelper.updateInfo(
                                    "" + id,
                                    "" + date,
                                    "" + description,
                                    "" + code);
                        }

                    }

                    startActivity(new Intent(EditBreakfastPromo.this, ViewBreakfastPromo.class));
                    Toast.makeText(EditBreakfastPromo.this, "Update Successfull", Toast.LENGTH_SHORT).show();
                }

            });

        }
    }
}