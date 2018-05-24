package com.example.saint.vacancies_mobile.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import com.example.saint.vacancies_mobile.R;

public abstract class BaseDialogFragment extends DialogFragment{

    protected abstract int getViewLayout();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(getViewLayout(), container, false);

        return view;
    }

    protected void removeDialogToolbarAndSetAnimation(){
        if(getDialog().getWindow() != null){
            getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
            getDialog().getWindow().getAttributes().windowAnimations = R.style.DialogFragmentAnimation;
        }
    }
}
