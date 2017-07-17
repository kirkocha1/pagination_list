package com.task.paginationlist.data.utils;

import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.task.paginationlist.R;

/**
 * Created by kirill on 11.07.17.
 */

public class ImageLoader implements ILoader {

    private Picasso picasso;

    public ImageLoader(Picasso picasso) {
        this.picasso = picasso;
    }

    @Override
    public void loadImage(String url, ImageView imageView) {
        picasso.load(url).placeholder(R.drawable.image_placeholder).into(imageView);
    }
}

