package com.gegprifti.android.numbertheoryalgorithms.fragments.common;


import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import com.gegprifti.android.numbertheoryalgorithms.fragments.TabFragmentAlgorithms;
import com.gegprifti.android.numbertheoryalgorithms.fragments.TabFragmentCalculator;


// https://developer.android.com/develop/ui/views/animations/vp2-migration
// https://developer.android.com/develop/ui/views/animations/screen-slide-2
// https://developer.android.com/guide/navigation/navigation-swipe-view-2


public class TabPagerAdapter extends FragmentStateAdapter {
    private final Fragment[] fragments = new Fragment[2];
    private final String[] pageTitles = new String[2];

    public TabPagerAdapter(FragmentActivity fragmentActivity) {
        super(fragmentActivity);
        // Initiate fragments.
        fragments[0] = new TabFragmentCalculator();
        fragments[1] = new TabFragmentAlgorithms();
        // Initiate tab names.
        pageTitles[0] = "CALCULATOR";
        pageTitles[1] = "ALGORITHMS";
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