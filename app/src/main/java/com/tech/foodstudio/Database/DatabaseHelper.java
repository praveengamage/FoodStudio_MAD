package com.tech.foodstudio.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.tech.foodstudio.Constants;
import com.tech.foodstudio.Model;

import java.util.ArrayList;


public class DatabaseHelper extends SQLiteOpenHelper {

    public DatabaseHelper(@Nullable Context context) {
        super(context, Constants.DB_NAME, null, Constants.DB_VERSION);

    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(Constants.CREATE_TABLE);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + Constants.TABLE_NAME);
        onCreate(db);

    }


    //insert infomation

    public long insertInfo(String date, String description, String code) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(Constants.DATE, date);
        values.put(Constants.DESCRIPTION, description);
        values.put(Constants.PRMOCODE, code);

        long id = db.insert(Constants.TABLE_NAME, null, values);
        db.close();
        return id;

    }


    //update infomation

    public void updateInfo(String id, String date, String description, String code) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(Constants.DATE, date);
        values.put(Constants.DESCRIPTION, description);
        values.put(Constants.PRMOCODE, code);

        db.update(Constants.TABLE_NAME, values, Constants.ID + " = ?", new String[]{id});
        db.close();

    }


    //delete informatin

    public void deleteInfo(String id) {

        SQLiteDatabase db = getWritableDatabase();
        db.delete(Constants.TABLE_NAME, Constants.ID + " = ? ", new String[]{id});
        db.close();

    }


    public ArrayList<Model> getAllData(String orderBy) {

        ArrayList<Model> arrayList = new ArrayList<>();

        //query for select all info in databse
        String selectQuery = "SELECT * FROM " + Constants.TABLE_NAME + " ORDER BY " + orderBy;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        //select all info from databes new gte the data from column
        if (cursor.moveToNext()) {

            do {

                Model model = new Model(

                        "" + cursor.getInt(cursor.getColumnIndex(Constants.ID)),
                        "" + cursor.getString(cursor.getColumnIndex(Constants.DATE)),
                        "" + cursor.getString(cursor.getColumnIndex(Constants.DESCRIPTION)),
                        "" + cursor.getString(cursor.getColumnIndex(Constants.PRMOCODE))
                );

                arrayList.add(model);
            } while (cursor.moveToNext());
        }

        db.close();
        return arrayList;
    }

}