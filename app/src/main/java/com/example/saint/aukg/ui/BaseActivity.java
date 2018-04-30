package com.example.saint.aukg.ui;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.saint.aukg.BuildConfig;
import com.example.saint.aukg.R;
import com.example.saint.aukg.data.models.TabPagerItem;
import com.example.saint.aukg.ui.suitable.SuitableFragment;
import com.example.saint.aukg.ui.vacancies.VacanciesFragment;
import com.example.saint.aukg.ui.main.ArrayPagerAdapter;
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

    private Drawer drawer;
    private Toolbar toolbar;
    private AccountHeader header;
    private ProfileDrawerItem profileDrawerItem;
    private PrimaryDrawerItem electedDrawerItem, exitDrawerItem;

    private ViewPager viewPager;
    private TabLayout tabLayout;

    private ArrayList<TabPagerItem> tabs;

    @Override
    public void setContentView(View view) {
        super.setContentView(view);
        setContentView(getViewLayout());
    }

    protected void getDrawer(){

        profileDrawerItem = new ProfileDrawerItem()
                .withName(R.string.vacancy)
                .withEmail(String.format(getResources().getString(R.string.version), BuildConfig.VERSION_NAME))
                .withIcon(R.drawable.logo);

        header = new AccountHeaderBuilder()
                .withActivity(this)
                .withHeaderBackground(R.color.colorPurpleLight)
                .addProfiles(profileDrawerItem)
                .withSelectionListEnabledForSingleProfile(false)
                .build();

        electedDrawerItem = new PrimaryDrawerItem()
                .withName(R.string.elected)
                .withIdentifier(1)
                .withIcon(R.drawable.ic_star_black_24dp);

        exitDrawerItem = new PrimaryDrawerItem()
                .withName(R.string.exit)
                .withIdentifier(2)
                .withIcon(R.drawable.ic_exit_to_app_black_24dp);

        drawer = new DrawerBuilder()
                .withActivity(this)
                .withOnDrawerItemClickListener(this)
                .withAccountHeader(header)
                .withSelectedItem(-1)
                .withToolbar(toolbar)
                .addDrawerItems(electedDrawerItem, new DividerDrawerItem(), exitDrawerItem)
                .build();

    }

    protected void getToolbar(String title, boolean back){

        toolbar = findViewById(getToolbarId());
        setSupportActionBar(toolbar);
        if(getSupportActionBar() != null){
            getSupportActionBar().setTitle(title);
        }
        getSupportActionBar().setDisplayHomeAsUpEnabled(back);
    }

    protected void getTabLayout(){

        tabs = new ArrayList<>();

        tabs.add(new TabPagerItem(new VacanciesFragment(), getResources().getString(R.string.vac_per_day)));
        tabs.add(new TabPagerItem(new SuitableFragment(), getResources().getString(R.string.suitable)));

        viewPager = findViewById(R.id.viewPager);
        tabLayout = findViewById(R.id.tabLayout);

        ArrayPagerAdapter adapter = new ArrayPagerAdapter(getSupportFragmentManager(), tabs);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

    }

    @Override
    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
        return false;
    }
}
