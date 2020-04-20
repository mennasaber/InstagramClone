package com.example.instagramclone.Adapters;

import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SettingAdapter extends FragmentStatePagerAdapter {
    private List<Fragment> mFragmentList;
    private HashMap<Fragment, Integer> mFragments;
    private HashMap<String, Integer> mFragmentNumbers;
    private HashMap<Integer, String> mFragmentNames;

    public SettingAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
        mFragmentList = new ArrayList<>();
        mFragments = new HashMap<>();
        mFragmentNumbers = new HashMap<>();
        mFragmentNames = new HashMap<>();
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }

    public void addFragment(Fragment fragment, String fragmentName) {
        mFragmentList.add(fragment);
        mFragmentNames.put(mFragmentList.size() - 1, fragmentName);
        mFragmentNumbers.put(fragmentName, mFragmentList.size() - 1);
        mFragments.put(fragment, mFragmentList.size() - 1);
    }

    public Integer getFragmentNumber(String fragmentName) {
        if (mFragmentNumbers.containsKey(fragmentName)) {
            return mFragmentNumbers.get(fragmentName);
        }
        return null;
    }

    public String getFragmentName(Integer fragmentNumber) {
        if (mFragmentNames.containsKey(fragmentNumber)) {
            return mFragmentNames.get(fragmentNumber);
        }
        return null;
    }

    public Integer getFragmentNumber(Fragment fragment) {
        if (mFragments.containsKey(fragment)) {
            return mFragments.get(fragment);
        }
        return null;
    }
}
