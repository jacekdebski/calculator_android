package com.calculator;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.ArrayList;

public class FavoriteLocationsAdapter extends FragmentStatePagerAdapter {
    private ArrayList<FavoriteLocationFragment> mFavoriteLocationFragments;


    public FavoriteLocationsAdapter(@NonNull FragmentManager fragmentManager, ArrayList<FavoriteLocationFragment> favoriteLocationFragments) {
        super(fragmentManager);
        mFavoriteLocationFragments = favoriteLocationFragments;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return null;
    }

    @Override
    public int getCount() {
        return 0;
    }
}
