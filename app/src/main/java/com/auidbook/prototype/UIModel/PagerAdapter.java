package com.auidbook.prototype.UIModel;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.auidbook.prototype.AcceptDonationFragment;
import com.auidbook.prototype.HomeFragment;

/**
 * Created by mgundappan on 15-06-2016.
 */
public class PagerAdapter extends FragmentStatePagerAdapter {


    int mNumOfTabs;

    public PagerAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
               // TabFragment1 tab1 = new TabFragment1();
                HomeFragment homeFragment1 = new HomeFragment();
                return homeFragment1;
            case 1:
                HomeFragment homeFragment2 = new HomeFragment();
                return homeFragment2;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}
