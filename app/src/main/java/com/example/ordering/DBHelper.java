package com.example.ordering;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.miqbal.ordering.Order;

public class DBHelper extends SQLiteOpenHelper {

    public static final String Key_ID = "ID";
    public static final String Key_Value = "Quantity";
    public static final String Key_Name = "Name";
    public static final String Key_ContactNo = "Contact_No";
    public static final String Key_Address = "Address";
    public static final String Key_Payment = "Payment";

    public static final String DBName = "Orders";
    public static final String Table_Name = "Ordering";
    public static final int DBVersion = 1;


    String CREATE_ORDER_TABLE = "CREATE_TABLE" + Table_Name + "(" + Key_ID + "Integer primary key autoincrement ," + Key_Value + "TEXT," + Key_Name + "TEXT," +Key_ContactNo + "TEXT,"
            +Key_Address + "TEXT," +Key_Payment + "TEXT," + ")";

    public DBHelper(Context context) {
        super(context , DBName,null,DBVersion);

    }

    public void addOrder(Order orderObj) {
        Log.d("DBHelper","Inserting a order");

        //Get reference to the database
        SQLiteDatabase db = this.getWritableDatabase();

        //Create context values object and add values to it
        ContentValues values = new ContentValues();

        //key should be same as the column name
        values.put(Key_Value,orderObj.getValue());
        values.put(Key_Name,orderObj.getName());
        values.put(Key_ContactNo,orderObj.getContact());
        values.put(Key_Address,orderObj.getAddress());
        values.put(Key_Payment,orderObj.getPayment());

        //Insert value in table
        db.insert(Table_Name,null,values);

        //Close the connection
        db.close();
    }

    /*@Override
    public void onUpgrade(SQLiteDatabase DB, int oldVersion, int newVersion) {
        DB.execSQL("drop Table if exists Orders");
    }*/


    public Boolean updateorderdata(String name, String contact, String dob) {

        SQLiteDatabase DB = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("Quantity", Key_Value);
        contentValues.put("Name",Key_Name);
        contentValues.put("Contact_No",Key_ContactNo);
        contentValues.put("Address",Key_Address);
        contentValues.put("Payment",Key_Payment);

        Cursor cursor = DB.rawQuery(
                "Select * from Ordering where name = ?", new String[]{name});


        if (cursor.getCount() > 0) {


            long result = DB.update("Ordering",
                    contentValues, "name=?",
                    new String[]{name});

            if (result == -1) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }

    public Boolean deletedata(String name) {

        SQLiteDatabase DB = this.getWritableDatabase();


        Cursor cursor = DB.rawQuery(
                "Select * from Ordering where name = ?", new String[]{name});


        if (cursor.getCount() > 0) {


            long result = DB.delete("Ordering", "name=?",
                    new String[]{name});

            if (result == -1) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }


    public Cursor getAllOrders()
    {
        Log.d("DBHelper","View orders");

        //Get reference to the database
        SQLiteDatabase db = this.getReadableDatabase();

        //Create query
        String query = "Select * from " + Table_Name;

        //run query
        Cursor result = db.rawQuery(query,null);

        //return results
        return result;

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_ORDER_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
