package com.abdulkarim.loginapplication;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private final static String DATABASE_NAME = "user_login.db";
    private static final int DATABASE_VERSION = 1;

    public static final String TBL_USER = "user_table";

    public static final String USER_ID = "user_id";
    public static final String USER_LOGIN_NAME = "user_login_name";
    public static final String USER_NAME = "user_name";
    public static final String USER_PASSWORD = "user_password";
    public static final String USER_EMAIL = "user_email";
    public static final String USER_BIRTHDAY = "user_birthday";
    public static final String USER_GENDER = "user_gender";



    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_USER_TABLE = "CREATE TABLE " + TBL_USER + " ("
                + USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + USER_LOGIN_NAME + " TEXT UNIQUE,"
                + USER_NAME + " TEXT," + USER_PASSWORD + " TEXT," + USER_EMAIL + " TEXT UNIQUE," + USER_BIRTHDAY + " TEXT," + USER_GENDER + " TEXT" + ");";

        db.execSQL(CREATE_USER_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        String DROP_USER_TABLE = "DROP TABLE IF EXISTS " + TBL_USER;

        db.execSQL(DROP_USER_TABLE);
        onCreate(db);

    }

}
