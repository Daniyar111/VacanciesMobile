package com.example.saint.aukg;

import android.app.Application;
import android.content.Context;

import com.example.saint.aukg.data.NetworkBuilder;
import com.example.saint.aukg.data.RetrofitService;
import com.example.saint.aukg.data.db.SQLiteHelper;

public class AuApplication extends Application{
    private RetrofitService mService;
    private SQLiteHelper mSQLiteHelper;

    @Override
    public void onCreate() {
        super.onCreate();
        mService = NetworkBuilder.initService();
        mSQLiteHelper = new SQLiteHelper(getApplicationContext());
    }

    public static AuApplication get(Context context){
        return (AuApplication) context.getApplicationContext();
    }

    public RetrofitService getService() {
        return mService;
    }

    public SQLiteHelper getSQLiteHelper() {
        return mSQLiteHelper;
    }
}

