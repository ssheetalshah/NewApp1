package com.ics.newapp.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.ics.newapp.fregment.CompletedFragment;
import com.ics.newapp.fregment.PendingFragment;
import com.ics.newapp.fregment.RelatedFragment;
import com.ics.newapp.fregment.SignInFreg;
import com.ics.newapp.fregment.SignUpFreg;

public class NvigationAdapter extends FragmentPagerAdapter {
    private Context myContext;
    int totalTabs;

    public NvigationAdapter(Context context, FragmentManager fm, int totalTabs) {
        super(fm);
        myContext = context;
        this.totalTabs = totalTabs;
    }

    // this is for fragment tabs
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                RelatedFragment relatedFragment = new RelatedFragment();
                return relatedFragment;
            case 1:
                PendingFragment pendingFragment = new PendingFragment();
                return pendingFragment;
            case 2:
                CompletedFragment completedFragment = new CompletedFragment();
                return completedFragment;
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
