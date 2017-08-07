package com.zhang.newapplication.views.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.widget.BaseAdapter;

import java.util.ArrayList;

/**
 * Created by zhang_shuai on 2017/7/11.
 * Del:Fragment的适配器
 */

public class MyViewPagerAdapter extends FragmentPagerAdapter {

    private ArrayList<Fragment> mFragments;
    private ArrayList<String> mTitles;

    public MyViewPagerAdapter(FragmentManager fm, ArrayList<Fragment> mFragments, ArrayList<String> mTitles) {
        super(fm);
        this.mFragments = mFragments;
        this.mTitles = mTitles;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mTitles.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return (CharSequence) mTitles.get(position);
    }
}
