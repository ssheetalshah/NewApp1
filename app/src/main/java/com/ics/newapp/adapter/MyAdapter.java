package com.ics.newapp.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.ics.newapp.fregment.SignInFreg;
import com.ics.newapp.fregment.SignUpFreg;

public class MyAdapter extends FragmentPagerAdapter {

    private Context myContext;
    int totalTabs;

    public MyAdapter(Context context, FragmentManager fm, int totalTabs) {
        super(fm);
        myContext = context;
        this.totalTabs = totalTabs;
    }

    // this is for fragment tabs
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                SignInFreg signInFreg = new SignInFreg();
                return signInFreg;
            case 1:
                SignUpFreg signUpFreg = new SignUpFreg();
                return signUpFreg;
           /* case 2:
                MovieFragment movieFragment = new MovieFragment();
                return movieFragment;*/
            default:
                return null;
        }
    }
    // this counts total number of tabs
    @Override
    public int getCount() {
        return totalTabs;
    }
}
