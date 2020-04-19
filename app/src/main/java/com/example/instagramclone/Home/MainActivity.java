package com.example.instagramclone.Home;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.text.Layout;
import android.view.Menu;

import com.example.instagramclone.Adapters.ViewPagerAdapter;
import com.example.instagramclone.R;
import com.example.instagramclone.Utils.BottomNavigationViewHelper;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupBottomNavigationView();
        setupViewPager();
    }

    private void setupViewPager() {
        ViewPagerAdapter mViewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), R.layout.fragment_home);
        mViewPagerAdapter.addFragment(new CameraFragment());   //index 0
        mViewPagerAdapter.addFragment(new HomeFragment());     //index 1
        mViewPagerAdapter.addFragment(new MessagesFragment()); //index 2

        ViewPager mViewPager = findViewById(R.id.container);
        mViewPager.setAdapter(mViewPagerAdapter);

        TabLayout mTabLayout = findViewById(R.id.tabs);
        mTabLayout.setupWithViewPager(mViewPager);

        mTabLayout.getTabAt(0).setIcon(R.drawable.ic_camera);
        mTabLayout.getTabAt(1).setIcon(R.drawable.ic_icon);
        mTabLayout.getTabAt(2).setIcon(R.drawable.ic_messages);
    }

    private void setupBottomNavigationView() {
        BottomNavigationViewEx bottomNavigationViewEx = findViewById(R.id.bottomNavView_bar);
        BottomNavigationViewHelper.setupBottomNavigationView(bottomNavigationViewEx);
        BottomNavigationViewHelper.enableNavigation(this, bottomNavigationViewEx);
    }
}