package com.example.saint.aukg.data.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLiteHelper{
//public class SQLiteHelper extends SQLiteOpenHelper{
//
//    /**
//        1: Internet not working in main vacancies with "watched" and details vacancies;
//        2: Elected vacancies;
//        3: DialogFragment Search (radioButtons);
//     */
//
//    private static final String DB_NAME = "AU";
//    private static final int DB_VERSION = 2;
//
//    // Tables
//    private static final String TABLE_MAIN = "TABLE_MAIN";
//    private static final String TABLE_ELECTED = "TABLE_ELECTED";
//    private static final String TABLE_SEARCH = "TABLE_SEARCH";
//
//    private static final String ID = "_id";
//
//    // Variables of table main && elected
//    private static final String PID = "PID";
//    private static final String PROFESSION = "PROFESSION";
//    private static final String DATE = "DATE";
//    private static final String HEADER = "HEADER";
//    private static final String SALARY = "SALARY";
//    private static final String CHECKBOX_ELECTED = "CHECKBOX_ELECTED";
//    private static final String WATCHED = "WATCHED";
//    private static final String PROFILE = "PROFILE";
//    private static final String SITE_ADDRESS = "SITE_ADDRESS";
//    private static final String TELEPHONE = "TELEPHONE";
//    private static final String BODY = "BODY";
//
//    // Variables of table search
//    private static final String REGIME_ANY = "REGIME_ANY";
//    private static final String FULL = "FULL";
//    private static final String FLEXIBLE = "FLEXIBLE";
//    private static final String REMOTELY = "REMOTELY";
//    private static final String NIGHT = "NIGHT";
//    private static final String SALARY_ANY = "SALARY_ANY";
//    private static final String FIVE_MORE = "FIVE_MORE";
//    private static final String TEN_MORE = "TEN_MORE";
//    private static final String THIRTY_MORE = "THIRTY_MORE";
//
//    // Creating main vacancies table
//    private static final String CREATE_TABLE_MAIN = "CREATE TABLE IF NOT EXISTS " +
//            TABLE_MAIN + "(" +
//            ID + " INTEGER_PRIMARY_KEY, " +
//            PID + " TEXT, " +
//            PROFILE + " TEXT, " +
//            PROFESSION + " TEXT, " +
//            DATE + " TEXT, " +
//            HEADER + " TEXT, " +
//            SALARY + " TEXT, " +
//            SITE_ADDRESS + " TEXT, " +
//            TELEPHONE + " TEXT, " +
//            CHECKBOX_ELECTED + " INTEGER, " +
//            BODY + " TEXT, " +
//            WATCHED + " TEXT);";
//
//    // Creating elected vacancies table
//    private static final String CREATE_TABLE_ELECTED = "CREATE TABLE IF NOT EXISTS " +
//            TABLE_ELECTED + "(" +
//            ID + " INTEGER_PRIMARY_KEY, " +
//            PID + " TEXT, " +
//            PROFILE + " TEXT, " +
//            PROFESSION + " TEXT, " +
//            DATE + " TEXT, " +
//            HEADER + " TEXT, " +
//            SALARY + " TEXT, " +
//            SITE_ADDRESS + " TEXT, " +
//            TELEPHONE + " TEXT, " +
//            CHECKBOX_ELECTED + " INTEGER, " +
//            BODY + " TEXT);";
//
//    // Creating search table
//    private static final String CREATE_TABLE_SEARCH = "CREATE TABLE IF NOT EXISTS " +
//            TABLE_SEARCH + "(" +
//            ID + " INTEGER_PRIMARY_KEY, " +
//            REGIME_ANY + " INTEGER, " +
//            FULL + " INTEGER, " +
//            FLEXIBLE + " INTEGER, " +
//            REMOTELY + " INTEGER, " +
//            NIGHT + " INTEGER, " +
//            SALARY_ANY + " INTEGER, " +
//            FIVE_MORE + " INTEGER, " +
//            TEN_MORE + " INTEGER, " +
//            THIRTY_MORE + " INTEGER);";
//
//    public SQLiteHelper(Context context) {
//        super(context, DB_NAME, null, DB_VERSION);
//    }
//
//    @Override
//    public void onCreate(SQLiteDatabase db) {
//        db.execSQL(CREATE_TABLE_MAIN);
//        db.execSQL(CREATE_TABLE_ELECTED);
//        db.execSQL(CREATE_TABLE_SEARCH);
//    }
//
//    @Override
//    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MAIN);
//        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ELECTED);
//        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SEARCH);
//        onCreate(db);
//    }
//
}
