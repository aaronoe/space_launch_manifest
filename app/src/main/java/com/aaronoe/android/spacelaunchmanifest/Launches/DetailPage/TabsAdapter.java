package com.aaronoe.android.spacelaunchmanifest.Launches.DetailPage;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.aaronoe.android.spacelaunchmanifest.Launches.MainLaunches.LaunchItem;

/**
 * Changes fragments in the detail view
 * Created by aaron on 9/14/2016.
 */
class TabsAdapter extends FragmentPagerAdapter {

    private LaunchItem mCurrentLaunchItem;

    public TabsAdapter(FragmentManager fm, LaunchItem currentLaunch) {
        super(fm);
        mCurrentLaunchItem = currentLaunch;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public Fragment getItem(int i) {
        switch(i) {
            case 0: return DetailpageDetailFragment.newInstance(mCurrentLaunchItem);
            case 1: return DetailpageMissionFragment.newInstance(mCurrentLaunchItem);
        }
        return null;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch(position) {
            case 0: return "DETAILS";
            case 1: return "MISSION";
        }
        return "";
    }
}