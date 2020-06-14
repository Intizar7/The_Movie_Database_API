package com.najimaddinova.moviesbyinteraktifkredi.Adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import java.util.ArrayList;
import java.util.List;

public class TabAdapter extends FragmentPagerAdapter {

    private final List<Fragment> moviesFragment = new ArrayList<>();
    private final List<String> moviesTitle = new ArrayList<>();

    public TabAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return moviesFragment.get(position);
    }

    @Override
    public int getCount() {
        return moviesTitle.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return moviesTitle.get(position);
    }

    public void addFragment(Fragment fragment, String title){
     moviesFragment.add(fragment);
     moviesTitle.add(title);
    }


}
