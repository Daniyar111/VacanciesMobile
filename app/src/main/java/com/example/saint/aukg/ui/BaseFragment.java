package com.example.saint.aukg.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.saint.aukg.R;

/**
 * Created by saint on 15.04.2018.
 */

public abstract class BaseFragment extends Fragment {

    protected abstract int getViewLayout();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(getViewLayout(), container, false);

        return view;
    }
}
