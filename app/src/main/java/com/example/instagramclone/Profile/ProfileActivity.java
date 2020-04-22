package com.example.instagramclone.Profile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;

import com.example.instagramclone.Adapters.GridViewAdapter;
import com.example.instagramclone.R;
import com.example.instagramclone.Setting.SettingActivity;
import com.example.instagramclone.Utils.BottomNavigationViewHelper;
import com.example.instagramclone.Utils.UniversalImageLoader;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

import java.util.ArrayList;

public class ProfileActivity extends AppCompatActivity {

    private static final int NUM_GRID_COLUMNS = 3;
    ArrayList<String> imagesURL;
    GridView gridView;
    ImageView mImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        mImageView = findViewById(R.id.profileImage);
        setupBottomNavigationView();
        setupToolbar();
        setupActivityWidget();
        setupProfileImage();
        setupImagesURL();
        setupGridView();
    }

    private void setupProfileImage() {
        String imageURL = "https://futureuniversity.com/wp-content/uploads/2017/11/android.png";
        UniversalImageLoader.setImage(imageURL, null, mImageView, "");
    }

    private void setupActivityWidget() {
        gridView = findViewById(R.id.imageGridView);
    }

    private void setupImagesURL() {
        imagesURL = new ArrayList<>();
        imagesURL.add("https://futureuniversity.com/wp-content/uploads/2017/11/android.png");
        imagesURL.add("https://storage.googleapis.com/gweb-uniblog-publish-prod/images/Android_symbol_green_2.max-1500x1500.png");
        imagesURL.add("https://elioplus.com/Images/Logos/f70e9f5e-f12c-41d0-bac7-44208e896e49/android.jpg_27_6_2018_12345.jpeg");

        imagesURL.add("https://futureuniversity.com/wp-content/uploads/2017/11/android.png");
        imagesURL.add("https://storage.googleapis.com/gweb-uniblog-publish-prod/images/Android_symbol_green_2.max-1500x1500.png");
        imagesURL.add("https://elioplus.com/Images/Logos/f70e9f5e-f12c-41d0-bac7-44208e896e49/android.jpg_27_6_2018_12345.jpeg");
        imagesURL.add("https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcTyPozXQc4-G4efCRIaNU7RHQoUy8SmzK_AafSXjgDPl4iA-Yja&usqp=CAU");
        imagesURL.add("https://futureuniversity.com/wp-content/uploads/2017/11/android.png");
        imagesURL.add("https://storage.googleapis.com/gweb-uniblog-publish-prod/images/Android_symbol_green_2.max-1500x1500.png");
        imagesURL.add("https://elioplus.com/Images/Logos/f70e9f5e-f12c-41d0-bac7-44208e896e49/android.jpg_27_6_2018_12345.jpeg");
        imagesURL.add("https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcTyPozXQc4-G4efCRIaNU7RHQoUy8SmzK_AafSXjgDPl4iA-Yja&usqp=CAU");
        imagesURL.add("https://futureuniversity.com/wp-content/uploads/2017/11/android.png");
        imagesURL.add("https://storage.googleapis.com/gweb-uniblog-publish-prod/images/Android_symbol_green_2.max-1500x1500.png");
        imagesURL.add("https://elioplus.com/Images/Logos/f70e9f5e-f12c-41d0-bac7-44208e896e49/android.jpg_27_6_2018_12345.jpeg");
        imagesURL.add("https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcTyPozXQc4-G4efCRIaNU7RHQoUy8SmzK_AafSXjgDPl4iA-Yja&usqp=CAU");
        imagesURL.add("https://futureuniversity.com/wp-content/uploads/2017/11/android.png");
        imagesURL.add("https://storage.googleapis.com/gweb-uniblog-publish-prod/images/Android_symbol_green_2.max-1500x1500.png");
        imagesURL.add("https://elioplus.com/Images/Logos/f70e9f5e-f12c-41d0-bac7-44208e896e49/android.jpg_27_6_2018_12345.jpeg");
        imagesURL.add("https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcTyPozXQc4-G4efCRIaNU7RHQoUy8SmzK_AafSXjgDPl4iA-Yja&usqp=CAU");
        imagesURL.add("https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcTyPozXQc4-G4efCRIaNU7RHQoUy8SmzK_AafSXjgDPl4iA-Yja&usqp=CAU");
    }

    private void setupGridView() {
        int gridWidth = getResources().getDisplayMetrics().widthPixels;
        int imageWidth = gridWidth / NUM_GRID_COLUMNS;

        GridViewAdapter mAdapter = new GridViewAdapter(this, R.layout.layout_gridview_image, imagesURL, "");
        gridView.setAdapter(mAdapter);
        gridView.setColumnWidth(imageWidth);
    }

    private void setupToolbar() {
        Toolbar toolbar = findViewById(R.id.profile_toolbar);
        setSupportActionBar(toolbar);
        ImageView imageView = findViewById(R.id.options);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProfileActivity.this, SettingActivity.class);
                startActivity(intent);
            }
        });
    }

    private void setupBottomNavigationView() {
        BottomNavigationViewEx bottomNavigationViewEx = findViewById(R.id.bottomNavView_bar);
        BottomNavigationViewHelper.setupBottomNavigationView(bottomNavigationViewEx);
        BottomNavigationViewHelper.enableNavigation(this, bottomNavigationViewEx);
    }
}