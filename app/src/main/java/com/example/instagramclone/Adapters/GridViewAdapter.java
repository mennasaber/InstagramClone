package com.example.instagramclone.Adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


import com.example.instagramclone.R;
import com.example.instagramclone.Utils.SquareImageView;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

import java.util.ArrayList;


public class GridViewAdapter extends ArrayAdapter<String> {
    private LayoutInflater mLayoutInflater;
    private String append;
    private int layoutRecourse;


    public GridViewAdapter(@NonNull Context context, int resource, @NonNull ArrayList<String> imagesURL, String append) {
        super(context, resource, imagesURL);
        this.append = append;
        layoutRecourse = resource;
        this.mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        final ViewHolder holder;
        if (convertView == null) {
            convertView = mLayoutInflater.inflate(layoutRecourse, parent, false);
            holder = new ViewHolder();
            holder.mProgressBar = convertView.findViewById(R.id.imageProgressBar);
            holder.mImageView = convertView.findViewById(R.id.profileImage);
            convertView.setTag(holder); // save it in memory
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        String currentURL = getItem(position);
        ImageLoader mImageLoader = ImageLoader.getInstance();
        mImageLoader.displayImage(append + currentURL, holder.mImageView, new ImageLoadingListener() {
            @Override
            public void onLoadingStarted(String imageUri, View view) {
                if (holder.mProgressBar != null)
                    holder.mProgressBar.setVisibility(View.VISIBLE);
            }

            @Override
            public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
                if (holder.mProgressBar != null)
                    holder.mProgressBar.setVisibility(View.GONE);
            }

            @Override
            public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                if (holder.mProgressBar != null)
                    holder.mProgressBar.setVisibility(View.GONE);
            }

            @Override
            public void onLoadingCancelled(String imageUri, View view) {
                if (holder.mProgressBar != null)
                    holder.mProgressBar.setVisibility(View.GONE);
            }
        });
        return convertView;
    }

    private static class ViewHolder {
        SquareImageView mImageView;
        ProgressBar mProgressBar;
    }
}
