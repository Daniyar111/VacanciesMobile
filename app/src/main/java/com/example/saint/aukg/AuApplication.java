package com.example.saint.aukg;

import android.app.Application;
import android.content.Context;

import com.example.saint.aukg.data.NetworkBuilder;
import com.example.saint.aukg.data.RetrofitService;

public class AuApplication extends Application{
    private RetrofitService service;

    @Override
    public void onCreate() {
        super.onCreate();
        service = NetworkBuilder.initService();
    }

    public static AuApplication get(Context context){
        return (AuApplication) context.getApplicationContext();
    }

    public RetrofitService getService() {
        return service;
    }
}
