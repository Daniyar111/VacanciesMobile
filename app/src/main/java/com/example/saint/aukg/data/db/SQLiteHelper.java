package com.example.saint.aukg.data.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.saint.aukg.data.models.SearchButtonsModel;
import com.example.saint.aukg.data.models.VacancyModel;

import java.util.ArrayList;

import static android.webkit.ConsoleMessage.MessageLevel.LOG;

public class SQLiteHelper extends SQLiteOpenHelper{

    /**
        1: Internet not working in main vacancies with "watched" and details vacancies;
        2: Elected vacancies;
        3: DialogFragment Search (radioButtons);
     */

    private static final String DB_NAME = "AU";
    private static final int DB_VERSION = 2;

    // Tables
    private static final String TABLE_MAIN = "TABLE_MAIN";
    private static final String TABLE_ELECTED = "TABLE_ELECTED";
    private static final String TABLE_WATCHED = "TABLE_WATCHED";
    private static final String TABLE_SEARCH = "TABLE_SEARCH";
    private static final String TABLE_INTENT = "TABLE_INTENT";
    private static final String TABLE_POSITION = "TABLE_POSITION";

    private static final String ID = "_id";

    // Variables of table main && elected
    private static final String PID = "PID";
    private static final String PROFESSION = "PROFESSION";
    private static final String DATE = "DATE";
    private static final String HEADER = "HEADER";
    private static final String SALARY = "SALARY";
    private static final String CHECKBOX_ELECTED = "CHECKBOX_ELECTED";
    private static final String WATCHED = "WATCHED";
    private static final String PROFILE = "PROFILE";
    private static final String SITE_ADDRESS = "SITE_ADDRESS";
    private static final String TELEPHONE = "TELEPHONE";
    private static final String BODY = "BODY";

    // Variables of table search
    private static final String REGIME_SEARCH = "REGIME_SEARCH";
    private static final String SALARY_SEARCH = "SALARY_SEARCH";

    // Creating main vacancies table
    private static final String CREATE_TABLE_MAIN = "CREATE TABLE IF NOT EXISTS " +
            TABLE_MAIN + "(" +
            ID + " INTEGER_PRIMARY_KEY, " +
            PID + " TEXT, " +
            PROFILE + " TEXT, " +
            PROFESSION + " TEXT, " +
            DATE + " TEXT, " +
            HEADER + " TEXT, " +
            SALARY + " TEXT, " +
            SITE_ADDRESS + " TEXT, " +
            TELEPHONE + " TEXT, " +
            BODY + " TEXT);";

    // Creating watched table
    private static final String CREATE_TABLE_WATCHED = "CREATE TABLE IF NOT EXISTS " +
            TABLE_WATCHED + "(" +
            ID + " INTEGER_PRIMARY_KEY, " +
            PID + " TEXT, " +
            WATCHED + " INTEGER DEFAULT 0);";

    // Creating elected vacancies table
    private static final String CREATE_TABLE_ELECTED = "CREATE TABLE IF NOT EXISTS " +
            TABLE_ELECTED + "(" +
            ID + " INTEGER_PRIMARY_KEY, " +
            PID + " TEXT, " +
            CHECKBOX_ELECTED + " INTEGER DEFAULT 0);";

    // Creating search table
    private static final String CREATE_TABLE_SEARCH = "CREATE TABLE IF NOT EXISTS " +
            TABLE_SEARCH + "(" +
            ID + " INTEGER_PRIMARY_KEY, " +
            REGIME_SEARCH + " TEXT, " +
            SALARY_SEARCH + " TEXT);";

    // Creating detail vacancies table (intent)
    private static final String CREATE_TABLE_INTENT = "CREATE TABLE IF NOT EXISTS " +
            TABLE_INTENT + "(" +
            ID + " INTEGER_PRIMARY_KEY, " +
            PID + " TEXT, " +
            PROFILE + " TEXT, " +
            PROFESSION + " TEXT, " +
            DATE + " TEXT, " +
            HEADER + " TEXT, " +
            SALARY + " TEXT, " +
            SITE_ADDRESS + " TEXT, " +
            TELEPHONE + " TEXT, " +
            BODY + " TEXT);";

    public SQLiteHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_MAIN);
        db.execSQL(CREATE_TABLE_ELECTED);
        db.execSQL(CREATE_TABLE_SEARCH);
        db.execSQL(CREATE_TABLE_WATCHED);
        db.execSQL(CREATE_TABLE_INTENT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MAIN);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ELECTED);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SEARCH);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_WATCHED);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_INTENT);
        onCreate(db);
    }

    public void saveListWithoutInternet(ArrayList<VacancyModel> vacancyModels){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        db.delete(TABLE_MAIN, null, null);

        for (int i = 0; i < vacancyModels.size(); i++) {
            VacancyModel vacancyModel = vacancyModels.get(i);
            cv.put(PID, vacancyModel.getPid());
            cv.put(PROFESSION, vacancyModel.getProfession());
            cv.put(DATE, vacancyModel.getData());
            cv.put(HEADER, vacancyModel.getHeader());
            cv.put(SALARY, vacancyModel.getSalary());
            cv.put(PROFILE, vacancyModel.getProfile());
            cv.put(SITE_ADDRESS, vacancyModel.getSiteAddress());
            cv.put(TELEPHONE, vacancyModel.getTelephone());
            cv.put(BODY, vacancyModel.getBody());
            db.insert(TABLE_MAIN, null, cv);
        }
        db.close();
    }

    public ArrayList<VacancyModel> getListWithoutInternet(){
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<VacancyModel> vacancyModels = new ArrayList<>();
        Cursor cursor = db.query(TABLE_MAIN, null, null, null, null, null, null);

        if(cursor.moveToFirst()){
            int pidIndex = cursor.getColumnIndex(PID);
            int professionIndex = cursor.getColumnIndex(PROFESSION);
            int dateIndex = cursor.getColumnIndex(DATE);
            int headerIndex = cursor.getColumnIndex(HEADER);
            int salaryIndex = cursor.getColumnIndex(SALARY);
            int profileIndex = cursor.getColumnIndex(PROFILE);
            int siteAddressIndex = cursor.getColumnIndex(SITE_ADDRESS);
            int telephoneIndex = cursor.getColumnIndex(TELEPHONE);
            int bodyIndex = cursor.getColumnIndex(BODY);
            do{
                VacancyModel vacancyModel = new VacancyModel();
                String pid = cursor.getString(pidIndex);
                String profession = cursor.getString(professionIndex);
                String date = cursor.getString(dateIndex);
                String header = cursor.getString(headerIndex);
                String salary = cursor.getString(salaryIndex);
                String profile = cursor.getString(profileIndex);
                String siteAddress = cursor.getString(siteAddressIndex);
                String telephone = cursor.getString(telephoneIndex);
                String body = cursor.getString(bodyIndex);
                vacancyModel.setPid(pid);
                vacancyModel.setProfession(profession);
                vacancyModel.setData(date);
                vacancyModel.setHeader(header);
                vacancyModel.setSalary(salary);
                vacancyModel.setProfile(profile);
                vacancyModel.setSiteAddress(siteAddress);
                vacancyModel.setTelephone(telephone);
                vacancyModel.setBody(body);
                vacancyModels.add(vacancyModel);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        return vacancyModels;
    }

    public void saveWatchedVacancy(String pid, boolean watched){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(PID, pid);
        cv.put(WATCHED, watched);
        Log.d("SAVE", "watched: " + watched);
        db.insert(TABLE_WATCHED, null, cv);
        db.close();
    }

    public boolean getWatchedVacancy(String pid){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.query(TABLE_WATCHED, null, PID + "='" + pid + "'", null, null, null, null);

        boolean isWatched = false;
        if(cursor.moveToFirst()){
            int watchedIndex = cursor.getColumnIndex(WATCHED);
            do{
                String watched = cursor.getString(watchedIndex);
                if (watched.equals("1")){
                    isWatched = true;
                }
                Log.d("GET_WATCHED", "pid: " + pid + " " + watched);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return isWatched;
    }

    public void saveElectedVacancy(String pid, boolean elected){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(PID, pid);
        cv.put(CHECKBOX_ELECTED, elected);
        Log.d("SAVE", "elected: " + elected);
        db.insert(TABLE_ELECTED, null, cv);
        db.close();
    }

    public boolean getElectedVacancy(String pid){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.query(TABLE_ELECTED, null, PID + "='" + pid + "'", null, null, null, null);

        boolean isElected = false;
        if(cursor.moveToFirst()){
            int electedIndex = cursor.getColumnIndex(CHECKBOX_ELECTED);
            do{
                String elected = cursor.getString(electedIndex);
                if(elected.equals("1")){
                    isElected = true;
                }
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return isElected;
    }

    public ArrayList<VacancyModel> getElectedVacancies(){
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<VacancyModel> vacancyModels = new ArrayList<>();
        Cursor cursor = db.query(TABLE_ELECTED, null, null, null, null, null, null);

        if(cursor.moveToFirst()){
            int pidIndex = cursor.getColumnIndex(PID);
            int professionIndex = cursor.getColumnIndex(PROFESSION);
            int dateIndex = cursor.getColumnIndex(DATE);
            int headerIndex = cursor.getColumnIndex(HEADER);
            int salaryIndex = cursor.getColumnIndex(SALARY);
            int profileIndex = cursor.getColumnIndex(PROFILE);
            int siteAddressIndex = cursor.getColumnIndex(SITE_ADDRESS);
            int telephoneIndex = cursor.getColumnIndex(TELEPHONE);
            int bodyIndex = cursor.getColumnIndex(BODY);
            do{
                VacancyModel vacancyModel = new VacancyModel();
                String pid = cursor.getString(pidIndex);
                String profession = cursor.getString(professionIndex);
                String date = cursor.getString(dateIndex);
                String header = cursor.getString(headerIndex);
                String salary = cursor.getString(salaryIndex);
                String profile = cursor.getString(profileIndex);
                String siteAddress = cursor.getString(siteAddressIndex);
                String telephone = cursor.getString(telephoneIndex);
                String body = cursor.getString(bodyIndex);
                vacancyModel.setPid(pid);
                vacancyModel.setProfession(profession);
                vacancyModel.setData(date);
                vacancyModel.setHeader(header);
                vacancyModel.setSalary(salary);
                vacancyModel.setProfile(profile);
                vacancyModel.setSiteAddress(siteAddress);
                vacancyModel.setTelephone(telephone);
                vacancyModel.setBody(body);
                vacancyModels.add(vacancyModel);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        return vacancyModels;
    }

    public void deleteElectedVacancy(String pid){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_ELECTED, PID + "='" + pid + "'", null);
        db.close();
    }

    public void saveRadioButtons(SearchButtonsModel searchButtonsModel){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        db.delete(TABLE_SEARCH, null, null);

        cv.put(REGIME_SEARCH, searchButtonsModel.getRegime());
        cv.put(SALARY_SEARCH, searchButtonsModel.getSalary());

        db.insert(TABLE_SEARCH, null, cv);
        db.close();
    }

    public SearchButtonsModel getRadioButtons(){
        SQLiteDatabase db = this.getWritableDatabase();
        SearchButtonsModel searchButtonsModel = new SearchButtonsModel();
        Cursor cursor = db.query(TABLE_SEARCH, null, null, null, null, null, null);

        if(cursor.moveToFirst()){
            int regimeIndex = cursor.getColumnIndex(REGIME_SEARCH);
            int salaryIndex = cursor.getColumnIndex(SALARY_SEARCH);
            do{
                String regime = cursor.getString(regimeIndex);
                String salary = cursor.getString(salaryIndex);
                searchButtonsModel.setRegime(regime);
                searchButtonsModel.setSalary(salary);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        return searchButtonsModel;
    }

    public void saveVacanciesForDetails(ArrayList<VacancyModel> vacancyModels){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        db.delete(TABLE_INTENT, null, null);

        for (int i = 0; i < vacancyModels.size(); i++) {
            VacancyModel vacancyModel = vacancyModels.get(i);
            cv.put(PID, vacancyModel.getPid());
            cv.put(PROFESSION, vacancyModel.getProfession());
            cv.put(DATE, vacancyModel.getData());
            cv.put(HEADER, vacancyModel.getHeader());
            cv.put(SALARY, vacancyModel.getSalary());
            cv.put(PROFILE, vacancyModel.getProfile());
            cv.put(SITE_ADDRESS, vacancyModel.getSiteAddress());
            cv.put(TELEPHONE, vacancyModel.getTelephone());
            cv.put(BODY, vacancyModel.getBody());
            db.insert(TABLE_INTENT, null, cv);
        }
        db.close();
    }

    public ArrayList<VacancyModel> getVacanciesForDetails(){
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<VacancyModel> vacancyModels = new ArrayList<>();
        Cursor cursor = db.query(TABLE_INTENT, null, null, null, null, null, null);

        if(cursor.moveToFirst()){
            int pidIndex = cursor.getColumnIndex(PID);
            int professionIndex = cursor.getColumnIndex(PROFESSION);
            int dateIndex = cursor.getColumnIndex(DATE);
            int headerIndex = cursor.getColumnIndex(HEADER);
            int salaryIndex = cursor.getColumnIndex(SALARY);
            int profileIndex = cursor.getColumnIndex(PROFILE);
            int siteAddressIndex = cursor.getColumnIndex(SITE_ADDRESS);
            int telephoneIndex = cursor.getColumnIndex(TELEPHONE);
            int bodyIndex = cursor.getColumnIndex(BODY);
            do{
                VacancyModel vacancyModel = new VacancyModel();
                String pid = cursor.getString(pidIndex);
                String profession = cursor.getString(professionIndex);
                String date = cursor.getString(dateIndex);
                String header = cursor.getString(headerIndex);
                String salary = cursor.getString(salaryIndex);
                String profile = cursor.getString(profileIndex);
                String siteAddress = cursor.getString(siteAddressIndex);
                String telephone = cursor.getString(telephoneIndex);
                String body = cursor.getString(bodyIndex);
                vacancyModel.setPid(pid);
                vacancyModel.setProfession(profession);
                vacancyModel.setData(date);
                vacancyModel.setHeader(header);
                vacancyModel.setSalary(salary);
                vacancyModel.setProfile(profile);
                vacancyModel.setSiteAddress(siteAddress);
                vacancyModel.setTelephone(telephone);
                vacancyModel.setBody(body);
                vacancyModels.add(vacancyModel);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        return vacancyModels;
    }
}
