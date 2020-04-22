package com.example.instagramclone.Setting;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.example.instagramclone.Adapters.SettingAdapter;
import com.example.instagramclone.Profile.EditProfileFragment;
import com.example.instagramclone.R;
import com.example.instagramclone.Profile.SignOutFragment;
import com.example.instagramclone.Utils.BottomNavigationViewHelper;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

import java.util.ArrayList;

public class SettingActivity extends AppCompatActivity {
    ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        mViewPager = findViewById(R.id.container);
        mViewPager.setVisibility(View.GONE);

        setupBottomNavigationView();
        setupToolbar();
        setupSettingOptions(SettingActivity.this);
        setupFragment();
    }

    private void setupViewPager(Integer fragmentNumber) {
        RelativeLayout relativeLayout = findViewById(R.id.relContainer);
        relativeLayout.setVisibility(View.GONE);
        mViewPager.setVisibility(View.VISIBLE);
        mViewPager.setCurrentItem(fragmentNumber);
    }

    private void setupSettingOptions(Context mContext) {
        ListView mListView = findViewById(R.id.settingLV);
        ArrayList<String> mOptions = new ArrayList<>();
        mOptions.add("Edit Profile");
        mOptions.add("Sign Out");
        ArrayAdapter mArrayAdapter = new ArrayAdapter(mContext, android.R.layout.simple_list_item_1, mOptions);
        mListView.setAdapter(mArrayAdapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                setupViewPager(i);
            }
        });
    }

    private void setupFragment() {
        SettingAdapter settingAdapter = new SettingAdapter(getSupportFragmentManager(), R.layout.fragment_edit_profile);
        settingAdapter.addFragment(new EditProfileFragment(), "Edit Profile");
        settingAdapter.addFragment(new SignOutFragment(), "Sign Out");
        mViewPager.setAdapter(settingAdapter);
    }

    private void setupBottomNavigationView() {
        BottomNavigationViewEx bottomNavigationViewEx = findViewById(R.id.bottomNavView_bar);
        BottomNavigationViewHelper.setupBottomNavigationView(bottomNavigationViewEx);
        BottomNavigationViewHelper.enableNavigation(this, bottomNavigationViewEx);
    }

    private void setupToolbar() {
        Toolbar toolbar = findViewById(R.id.profile_toolbar);
        setSupportActionBar(toolbar);
        ImageView imageView = findViewById(R.id.back);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}