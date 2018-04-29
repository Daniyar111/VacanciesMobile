package com.example.saint.aukg.data.models;

import android.support.v4.app.Fragment;

/**
 * Created by saint on 15.04.2018.
 */

public class TabPagerItem {

    private final Fragment fragment;
    private final CharSequence charSequence;

    public TabPagerItem(Fragment fragment, CharSequence charSequence) {
        this.fragment = fragment;
        this.charSequence = charSequence;
    }

    public Fragment getFragment() {
        return fragment;
    }

    public CharSequence getCharSequence() {
        return charSequence;
    }
}
