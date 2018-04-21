package com.example.saint.aukg.ui.main;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.saint.aukg.models.TabPagerItem;

import java.util.ArrayList;

/**
 * Created by saint on 15.04.2018.
 */

public class ArrayPagerAdapter extends FragmentStatePagerAdapter{

    private ArrayList<TabPagerItem> tabs;

    public ArrayPagerAdapter(FragmentManager fm, ArrayList<TabPagerItem> tabs) {
        super(fm);
        this.tabs = tabs;
    }

    @Override
    public Fragment getItem(int position) {
        return tabs.get(position).getFragment();
    }

    @Override
    public int getCount() {
        return tabs.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabs.get(position).getCharSequence();
    }
}
