package com.example.saint.aukg.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.saint.aukg.R;
import com.example.saint.aukg.ui.adapters.MainVacanciesAdapter;

import java.util.ArrayList;

/**
 * Created by saint on 15.04.2018.
 */

public class VacanciesFragment extends BaseFragment {

    @Override
    protected int getViewLayout() {
        return R.layout.fragment_vacancies;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getAdapter(view);
    }

    private void getAdapter(View view){

        ArrayList<String> arrayList = new ArrayList<>();

        arrayList.add("fsdkjf");
        arrayList.add("dsfds");
        arrayList.add("fsdkwwerdsfjf");
        arrayList.add("vvv");
        arrayList.add("fdsfsfwe");
        arrayList.add("fdsqwe");
        arrayList.add("vgerggervv");

        MainVacanciesAdapter adapter = new MainVacanciesAdapter(arrayList);
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);

    }
}
