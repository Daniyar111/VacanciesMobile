package com.example.saint.vacancies_mobile.ui;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.saint.vacancies_mobile.BuildConfig;
import com.example.saint.vacancies_mobile.R;
import com.example.saint.vacancies_mobile.data.models.TabPagerItem;
import com.example.saint.vacancies_mobile.ui.elected.ElectedActivity;
import com.example.saint.vacancies_mobile.ui.suitable.SuitableFragment;
import com.example.saint.vacancies_mobile.ui.vacancies.VacanciesFragment;
import com.example.saint.vacancies_mobile.ui.main.ArrayPagerAdapter;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

import java.util.ArrayList;

/**
 * Created by saint on 15.04.2018.
 */

public abstract class BaseActivity extends AppCompatActivity implements Drawer.OnDrawerItemClickListener {

    protected abstract int getViewLayout();
    protected abstract int getToolbarId();

    private Drawer mDrawer;
    private Toolbar mToolbar;
    private AccountHeader mHeader;
    private ProfileDrawerItem mProfileDrawerItem;
    private PrimaryDrawerItem mElectedDrawerItem, mExitDrawerItem;

    private ViewPager mViewPager;
    private TabLayout mTabLayout;

    private ArrayList<TabPagerItem> mTabs;

    @Override
    public void setContentView(View view) {
        super.setContentView(view);
        setContentView(getViewLayout());
    }

    protected void getDrawer(){

        mProfileDrawerItem = new ProfileDrawerItem()
                .withName(R.string.vacancies)
                .withEmail(String.format(getResources().getString(R.string.version), BuildConfig.VERSION_NAME))
                .withIcon(R.mipmap.ic_launcher_round);

        mHeader = new AccountHeaderBuilder()
                .withActivity(this)
                .withHeaderBackground(R.color.colorPurpleLight)
                .addProfiles(mProfileDrawerItem)
                .withSelectionListEnabledForSingleProfile(false)
                .build();

        mElectedDrawerItem = new PrimaryDrawerItem()
                .withName(R.string.elected)
                .withIdentifier(1)
                .withIcon(R.drawable.ic_star_black_24dp);

        mExitDrawerItem = new PrimaryDrawerItem()
                .withName(R.string.exit)
                .withIdentifier(2)
                .withIcon(R.drawable.ic_exit_to_app_black_24dp);

        mDrawer = new DrawerBuilder()
                .withActivity(this)
                .withOnDrawerItemClickListener(this)
                .withAccountHeader(mHeader)
                .withSelectedItem(-1)
                .withToolbar(mToolbar)
                .addDrawerItems(mElectedDrawerItem, new DividerDrawerItem(), mExitDrawerItem)
                .build();


    }

    protected void getToolbar(String title, boolean back){

        mToolbar = findViewById(getToolbarId());
        setSupportActionBar(mToolbar);
        if(getSupportActionBar() != null){
            getSupportActionBar().setTitle(title);
        }
        getSupportActionBar().setDisplayHomeAsUpEnabled(back);
    }

    protected void getTabLayout(){

        mTabs = new ArrayList<>();

        mTabs.add(new TabPagerItem(new VacanciesFragment(), getResources().getString(R.string.vac_per_day)));
        mTabs.add(new TabPagerItem(new SuitableFragment(), getResources().getString(R.string.suitable)));

        mViewPager = findViewById(R.id.viewPager);
        mTabLayout = findViewById(R.id.tabLayout);

        ArrayPagerAdapter adapter = new ArrayPagerAdapter(getSupportFragmentManager(), mTabs);
        mViewPager.setAdapter(adapter);
        mTabLayout.setupWithViewPager(mViewPager);

    }

    @Override
    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {

        switch ((int) drawerItem.getIdentifier()){
            case 1:
                Intent intent = new Intent(this, ElectedActivity.class);
                startActivity(intent);
                mDrawer.setSelection(-1);
                break;
            case 2:
                finish();
                break;
        }
        return false;
    }

    protected void switchFragment(Fragment fragment){
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragmentContainer, fragment)
                .commit();
    }
}
