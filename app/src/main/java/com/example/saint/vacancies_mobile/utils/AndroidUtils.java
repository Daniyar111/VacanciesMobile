package com.example.saint.vacancies_mobile.utils;

import android.content.Context;
import android.widget.Toast;

public final class AndroidUtils {

    public static void showLongToast(Context context, String message){
        showToast(context, message, Toast.LENGTH_LONG);
    }

    private static void showToast(Context context, String message, int length){
        Toast.makeText(context, message, length).show();
    }
}
