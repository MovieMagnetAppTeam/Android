package com.pam.pam_front.controller;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by ledsoon on 18.11.17.
 */

public class PagerAdapter extends FragmentPagerAdapter {

    private final Fragment[] fragments;

    public PagerAdapter(FragmentManager fragmentManager, Fragment[] fragments) {
        super(fragmentManager);
        this.fragments = fragments;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return fragments[position].getClass().getSimpleName();
    }

    @Override
    public Fragment getItem(int position) {
        return fragments[position];
    }

    @Override
    public int getCount() {
        return fragments.length;
    }
}