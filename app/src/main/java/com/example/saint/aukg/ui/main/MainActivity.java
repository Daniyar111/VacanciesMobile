package com.example.saint.aukg.ui.main;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.saint.aukg.R;
import com.example.saint.aukg.ui.BaseActivity;
import com.example.saint.aukg.ui.search.DialogNameFragment;

public class MainActivity extends BaseActivity {

    private DialogNameFragment searchFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getViewLayout());

        getToolbar();
        getDrawer();
        getTabLayout();
    }

    @Override
    protected int getViewLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected int getToolbarId() {
        return R.id.toolbar;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.search_action, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        searchFragment = new DialogNameFragment();
        searchFragment.show(getSupportFragmentManager(), "fragment");
        return super.onOptionsItemSelected(item);
    }

}
