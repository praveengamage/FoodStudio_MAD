package com.example.ordering;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class SQLiteHelper extends SQLiteOpenHelper {
    private static SQLiteHelper sqLiteHelper;

    private static final String DATABASE_NAME = "NoteDB";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "Note";
    private static final String COUNTER = "Counter";

    private static final String ID_FIELD = "id";
    private static final int ITEM_FIELD = 1;
    private static final String DESC_FIELD = "Note";
    private static final String DELETED_FIELD = "Counter";

    private static final DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss");

    public SQLiteHelper( Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static SQLiteHelper instanceOfDatabase(Context context)
    {
        if(sqLiteHelper == null)
            sqLiteHelper = new SQLiteHelper(context);

        return sqLiteHelper;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        StringBuilder sql;
        sql = new StringBuilder()
                .append("CREATE_TABLE")
                .append(TABLE_NAME)
                .append("(")
                .append(COUNTER)
                .append("INTEGER PRIMARY KEY AUTOINCREMENT")
                .append(ID_FIELD)
                .append("INT,")
                .append(ITEM_FIELD)
                .append("TEXT,")
                .append(DESC_FIELD)
                .append("TEXT,")
                .append(DELETED_FIELD)
                .append("TEXT,");
        sqLiteDatabase.execSQL(sql.toString());
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
//        switch (oldVersion)
//        {
//            case 1:
//                sqLiteDatabase.execSQL("ALTER TABLE"+ TABLE_NAME + "ADD COLUMN" + NEW_COLUMN + "TEXT");
//            case 1:
//                sqLiteDatabase.execSQL("ALTER TABLE"+ TABLE_NAME + "ADD COLUMN" + NEW_COLUMN + "TEXT");
//        }
    }

    public void addNoteToDatabase(Note note)
    {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(ID_FIELD, note.getId());
        contentValues.put(note.getTitle(), ITEM_FIELD);
        contentValues.put(DESC_FIELD,note.getDescription());
        contentValues.put(DELETED_FIELD,getStringFromDate(note.getDeleted()));

        sqLiteDatabase.insert(TABLE_NAME, null,contentValues);

    }

    public void populateNoteListArray()
    {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();

        try(Cursor result = sqLiteDatabase.rawQuery("SELECT * FROM " + TABLE_NAME,null))
        {
            if(result.getCount() != 0)
            {
                while(result.moveToNext())
                {
                    int id = result.getInt(1);
                    String item = result.getString(2);
                    String desc = result.getString(3);
                    String stringdeleted =result.getString(4);
                    Date deleted = getDateFromString(stringdeleted);
                    Note note = new Note(id,item,desc,deleted);
                    Note.noteArrayList.add(note);
                }
            }
        }
    }

    public void updateNoteINDB(Note note)
    {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ID_FIELD, note.getId());
        contentValues.put(note.getTitle(), ITEM_FIELD);
        contentValues.put(DESC_FIELD,note.getDescription());
        contentValues.put(DELETED_FIELD,getStringFromDate(note.getDeleted()));

        sqLiteDatabase.update(TABLE_NAME,contentValues,ID_FIELD+ "=?" ,new String[]{String.valueOf(note.getId())});

    }

    private String getStringFromDate(Date date) {
        if(date == null)
            return null;

        return dateFormat.format(date);
    }

    private Date getDateFromString(String string)
    {
        try
        {
            return dateFormat.parse(string);
        }
        catch (ParseException | NullPointerException e)
        {
            return null;
        }
    }
}
