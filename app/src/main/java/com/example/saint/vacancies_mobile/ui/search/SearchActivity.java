package com.example.saint.vacancies_mobile.ui.search;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.saint.vacancies_mobile.R;
import com.example.saint.vacancies_mobile.ui.BaseActivity;

public class SearchActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getViewLayout());

        getToolbar(getResources().getString(R.string.search), true);

        switchFragment(new SearchListFragment());
    }

    @Override
    protected int getViewLayout() {
        return R.layout.activity_search;
    }

    @Override
    protected int getToolbarId() {
        return R.id.toolbar;
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
