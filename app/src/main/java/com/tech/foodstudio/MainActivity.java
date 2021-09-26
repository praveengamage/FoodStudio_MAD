package com.tech.foodstudio;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button log;
    EditText editText1;
    EditText editText2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText1=(EditText)findViewById(R.id.email);
        editText2=(EditText)findViewById(R.id.pass);
        log=(Button)findViewById(R.id.login);

        log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(editText1.getText().toString().equals("customer") && editText2.getText().toString().equals("customer")){
                    Toast.makeText(MainActivity.this, "Successfully sign in", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivity.this, CusomerHome.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(MainActivity.this, "Sign in failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}