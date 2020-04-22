package com.example.instagramclone.Profile;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.instagramclone.R;
import com.example.instagramclone.Utils.UniversalImageLoader;
import com.nostra13.universalimageloader.core.ImageLoader;


public class EditProfileFragment extends Fragment {

    ImageView mImageView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_edit_profile, container, false);
        mImageView = view.findViewById(R.id.profileImage);
        setupProfileImage();
        return view;
    }

    private void setupProfileImage() {
        String imageURL = "https://futureuniversity.com/wp-content/uploads/2017/11/android.png";
        UniversalImageLoader.setImage(imageURL, null, mImageView, "");
    }


}