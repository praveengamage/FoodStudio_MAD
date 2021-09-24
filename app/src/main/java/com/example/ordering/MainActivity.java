package com.example.ordering;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView value;
    int count= 0;
    private Button button;

    private ListView noteListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initWidgets();
        loadFromDBToMemory();
        setNoteAdapter();
        setOnClickListner();

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

    private void setOnClickListner() {
        noteListView.setOnClickListener(new AdapterView.OnItemClickListener<>()
        {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long 1)
            {
                Note selectedNote = (Note) noteListView.getItemAtPosition(position);
                Intent editNoteIntent = new Intent(getApplicationContext(),itemdetails.class);
                editNoteIntent.putExtra(Note.NOTE_EDIT_EXTRA,selectedNote.getId());
                startActivity(editNoteIntent);
            }
        });
    }

    private void loadFromDBToMemory() {
        SQLiteHelper sqLiteHelper = SQLiteHelper.instanceOfDatabase(this);
        sqLiteHelper.populateNoteListArray();
    }

    private void setNoteAdapter() {
        NoteAdapter noteAdapter = new NoteAdapter(getApplicationContext(),Note.nonDeletedNotes());
        noteListView.setAdapter(noteAdapter);
    }

    private void initWidgets() {
        noteListView= findViewById(R.id.noteListView);
    }

    public void newNote(View view)
    {
        Intent newNoteIntent = new Intent(this, itemdetails.class);
        startActivity(newNoteIntent);
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        setNoteAdapter();
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