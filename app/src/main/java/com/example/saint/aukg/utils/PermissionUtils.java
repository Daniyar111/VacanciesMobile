package com.example.saint.aukg.utils;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

public final class PermissionUtils {

    private static boolean isVersionCode(){

        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.M;
    }

    private static void getRequestPermission(Context context, Activity activity, Intent intent){

        if(ContextCompat.checkSelfPermission(context, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.CALL_PHONE}, Constants.REQUEST_PHONE_CALL);
        }
        else{
            activity.startActivity(intent);
        }
    }

    public static void getPermissionForCalling(Context context, Activity activity, Intent intent){

        if(isVersionCode()){
            getRequestPermission(context, activity, intent);
        }
        else{
            activity.startActivity(intent);
        }
    }
}
