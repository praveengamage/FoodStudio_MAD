package com.example.ordering;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.miqbal.ordering.Order;

public class DBHelper extends SQLiteOpenHelper {

    public static final String Key_ID = "ID";
    public static final String Key_Value = "Quantity";
    public static final String Key_Name = "Name";
    public static final String Key_ContactNo = "Contact No";
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

        //Create context values object and add values to it

        //key should be same as the column name

        //Insert value in table

        //Close the connection
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
