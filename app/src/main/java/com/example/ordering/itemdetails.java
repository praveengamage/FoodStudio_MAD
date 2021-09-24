package com.example.ordering;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Date;

public class itemdetails extends AppCompatActivity {

    private EditText itemEditText , descEditText;
    private Button deleteButton;
    private Note selectedNote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_itemdetails);
        initWidgets();
        checkForEditNote();
    }

    private void checkForEditNote() {
        Intent previousIntent = getIntent();

        int passedNoteID = previousIntent.getIntExtra(Note.NOTE_EDIT_EXTRA,-1);
        selectedNote = Note.getNoteForID(passedNoteID);

        if(selectedNote != null)
        {
            itemEditText.setText(selectedNote.getTitle());
            descEditText.setText(selectedNote.getDescription());
        }
        else
        {
            deleteButton.setVisibility(View.VISIBLE);
        }
    }

    private void initWidgets() {
        itemEditText = findViewById(R.id.iteminsert);
        descEditText = findViewById(R.id.insertdetails);
        deleteButton = findViewById(R.id.btndelete);
    }

    public void saveNote(View view){

        SQLiteHelper sqLiteHelper = SQLiteHelper.instanceOfDatabase(this);
        String item = String.valueOf(itemEditText.getText());
        String description = String.valueOf(descEditText.getText());

        if(selectedNote == null){
            int id = Note.noteArrayList.size();
            Note newNote = new Note(id,item,description);
            Note.noteArrayList.add(newNote);
            sqLiteHelper.addNoteToDatabase(newNote);

        }
        else
        {
            selectedNote.setItem(item);
            selectedNote.setDescription(description);
            sqLiteHelper.updateNoteINDB(selectedNote);
        }

        finish();
    }

    public void deleteNote(View view) {
        selectedNote.setDeleted(new Date());
        SQLiteHelper sqLiteHelper = SQLiteHelper.instanceOfDatabase(this);
        sqLiteHelper.updateNoteINDB(selectedNote);
        finish();
    }

}