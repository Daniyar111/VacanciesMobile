package com.example.saint.aukg.data.models;

import android.support.v4.app.Fragment;

/**
 * Created by saint on 15.04.2018.
 */

public class TabPagerItem {

    private final Fragment mFragment;
    private final CharSequence mCharSequence;

    public TabPagerItem(Fragment fragment, CharSequence charSequence) {
        this.mFragment = fragment;
        this.mCharSequence = charSequence;
    }

    public Fragment getFragment() {
        return mFragment;
    }

    public CharSequence getCharSequence() {
        return mCharSequence;
    }
}
