package com.gegprifti.android.numbertheoryalgorithms.fragments.common;


import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.gegprifti.android.numbertheoryalgorithms.fragments.tabs.FragmentTabAbout;
import com.gegprifti.android.numbertheoryalgorithms.fragments.tabs.FragmentTabAlgorithms;
import com.gegprifti.android.numbertheoryalgorithms.fragments.tabs.FragmentTabCalculator;


// https://developer.android.com/develop/ui/views/animations/vp2-migration
// https://developer.android.com/develop/ui/views/animations/screen-slide-2
// https://developer.android.com/guide/navigation/navigation-swipe-view-2


public class TabPagerAdapter extends FragmentStateAdapter {
    private final Fragment[] fragments = new Fragment[3];
    private final String[] pageTitles = new String[3];

    public TabPagerAdapter(FragmentActivity fragmentActivity) {
        super(fragmentActivity);
        // Initiate fragments.
        fragments[0] = new FragmentTabCalculator();
        fragments[1] = new FragmentTabAlgorithms();
        fragments[2] = new FragmentTabAbout();
        // Initiate tab names.
        pageTitles[0] = "CALCULATOR";
        pageTitles[1] = "ALGORITHMS";
        pageTitles[2] = "ABOUT";
    }

    public String getPageTitle(int position) {
        return pageTitles[position];
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return fragments[position];
    }

    @Override
    public int getItemCount() {
        return fragments.length;
    }
}