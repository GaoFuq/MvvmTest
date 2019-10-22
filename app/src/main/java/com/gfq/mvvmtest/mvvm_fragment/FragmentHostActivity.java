package com.gfq.mvvmtest.mvvm_fragment;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.gfq.mvvmtest.R;

/**
 * create by 高富强
 * on {2019/10/18} {14:24}
 * desctapion:
 */
public class FragmentHostActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_host);
    }
}
