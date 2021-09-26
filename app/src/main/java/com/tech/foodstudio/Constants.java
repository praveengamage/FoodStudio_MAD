package com.tech.foodstudio;

public class Constants {

    //db name
    public static final String DB_NAME = "Promotion_INFO_DB.db";
    //db version
    public static final int DB_VERSION = 1;
    //db table
    public static final String TABLE_NAME = "Promotion_INFO";
    //table column
    public static final String ID = "ID";
    public static final String DATE = "date";
    public static final String DESCRIPTION = "description";
    public static final String PRMOCODE = "promo_code";

    //CREATE QUERY for table
    public static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " ("
            + ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + DATE + " TEXT,"
            + DESCRIPTION + " TEXT,"
            + PRMOCODE + " TEXT"
            + ");";



}
