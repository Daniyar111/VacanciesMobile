package com.example.saint.vacancies_mobile.ui.elected;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.saint.vacancies_mobile.AuApplication;
import com.example.saint.vacancies_mobile.R;
import com.example.saint.vacancies_mobile.data.db.SQLiteHelper;
import com.example.saint.vacancies_mobile.data.models.VacancyModel;
import com.example.saint.vacancies_mobile.ui.BaseFragment;
import com.example.saint.vacancies_mobile.ui.details.DetailsActivity;
import com.example.saint.vacancies_mobile.ui.MainVacanciesAdapter;
import com.example.saint.vacancies_mobile.ui.vacancies.VacanciesAdapterCallback;

import java.util.ArrayList;

public class ElectedFragment extends BaseFragment implements VacanciesAdapterCallback{

    private Context mContext;
    private RecyclerView mRecyclerView;
    private SQLiteHelper mSQLiteHelper;
    private ArrayList<VacancyModel> mVacancyModels = new ArrayList<>();
    private MainVacanciesAdapter mAdapter;

    @Override
    protected int getViewLayout() {
        return R.layout.fragment_elected;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if(getActivity() != null){
            mContext = getActivity().getApplicationContext();
        }
        mSQLiteHelper = AuApplication.get(mContext).getSQLiteHelper();

        getRecyclerView(view);
    }

    private void getRecyclerView(View view){

        mRecyclerView = view.findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }


    @Override
    public void onVacancyClicked(ArrayList<VacancyModel> vacancyModels, int position) {

        Intent intent = new Intent(mContext, DetailsActivity.class);
        intent.putParcelableArrayListExtra("vacancy_models", vacancyModels);
        intent.putExtra("position", position);
        startActivity(intent);
    }

    @Override
    public void onResume() {
        super.onResume();

        getVacancies();
    }

    private void getVacancies(){
        mVacancyModels = mSQLiteHelper.getElectedVacancies();
        mAdapter = new MainVacanciesAdapter(mContext, mVacancyModels, ElectedFragment.this, false);
        mRecyclerView.setAdapter(mAdapter);
    }
}
