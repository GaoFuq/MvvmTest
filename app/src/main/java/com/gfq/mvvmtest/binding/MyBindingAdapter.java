package com.gfq.mvvmtest.binding;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.gfq.mvvmtest.R;


public class MyBindingAdapter  {

    @BindingAdapter({"url"})
    public static void setImgUrl(ImageView view, String url) {
        Glide.with(view.getContext()).load(url).apply(RequestOptions.placeholderOf(R.mipmap.ic_launcher)).into(view);
    }


}
