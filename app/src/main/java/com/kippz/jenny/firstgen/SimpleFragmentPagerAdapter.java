package com.kippz.jenny.firstgen;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by Jenny on 07/10/2016.
 */

public class SimpleFragmentPagerAdapter extends FragmentPagerAdapter{

    private Context mContext;

    final int PAGE_COUNT = 2;

    private String tabTitles[] = new String[] { "Set", "Type" };

    public SimpleFragmentPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new SetFragment();
        } else {
            return new TypeFragment();
        }
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }
}
