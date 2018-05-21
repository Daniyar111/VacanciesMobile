package com.example.saint.aukg.ui.elected;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.saint.aukg.AuApplication;
import com.example.saint.aukg.R;
import com.example.saint.aukg.data.db.SQLiteHelper;
import com.example.saint.aukg.data.models.VacancyModel;
import com.example.saint.aukg.ui.BaseActivity;

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

        mSQLiteHelper = AuApplication.get(getApplicationContext()).getSQLiteHelper();
        mVacancyModels = mSQLiteHelper.getElectedVacancies();
        if(mVacancyModels.size() == 0){
            switchFragment(new EmptyFragment());
        }
        else{
            switchFragment(new ElectedFragment());
        }
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
}