package com.example.saint.vacancies_mobile.ui.elected;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.saint.vacancies_mobile.StartApplication;
import com.example.saint.vacancies_mobile.R;
import com.example.saint.vacancies_mobile.data.db.SQLiteHelper;
import com.example.saint.vacancies_mobile.data.models.VacancyModel;
import com.example.saint.vacancies_mobile.ui.BaseActivity;

import java.util.ArrayList;

public class ElectedActivity extends BaseActivity {

    private SQLiteHelper mSQLiteHelper;
    private ArrayList<VacancyModel> mVacancyModels;

    @Override
    protected int getViewLayout() {
        return R.layout.activity_elected;
    }

    @Override
    protected int getToolbarId() {
        return R.id.toolbar;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getViewLayout());

        getToolbar(getResources().getString(R.string.elected), true);

        mSQLiteHelper = StartApplication.get(getApplicationContext()).getSQLiteHelper();
        mVacancyModels = mSQLiteHelper.getElectedVacancies();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case android.R.id.home:
                onBackPressed();
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void readyFragments(){
        if(mVacancyModels.size() == 0){
            switchFragment(new EmptyFragment());
        }
        else{
            switchFragment(new ElectedFragment());
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        readyFragments();
    }
}
