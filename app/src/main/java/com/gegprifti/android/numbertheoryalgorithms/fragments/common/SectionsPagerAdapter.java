package com.gegprifti.android.numbertheoryalgorithms.fragments.common;


import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import java.util.ArrayList;
import java.util.List;


// https://developer.android.com/develop/ui/views/animations/vp2-migration
// https://developer.android.com/develop/ui/views/animations/screen-slide-2
// https://developer.android.com/guide/navigation/navigation-swipe-view-2


public class SectionsPagerAdapter extends FragmentStateAdapter {
    private final List<Fragment> fragments = new ArrayList<>();
    private final List<String> fragmentNames = new ArrayList<>();

    public SectionsPagerAdapter(Fragment fragment) {
        super(fragment);
    }

    public void addFragment(Fragment fragment, String name) {
        fragments.add(fragment);
        fragmentNames.add(name);
    }

    public Fragment getItemByName (String name) {
        int indexOfClassName = fragmentNames.indexOf(name);
        return this.createFragment(indexOfClassName);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return fragments.get(position);
    }

    @Override
    public int getItemCount() {
        return fragments.size();
    }
}