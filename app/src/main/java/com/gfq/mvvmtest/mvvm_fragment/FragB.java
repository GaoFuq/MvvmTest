package com.gfq.mvvmtest.mvvm_fragment;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gfq.mvvmtest.MyViewModel;
import com.gfq.mvvmtest.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragB extends Fragment {



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_b, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        MyViewModel viewModel = ViewModelProviders.of(getActivity()).get(MyViewModel.class);
        viewModel.update();
    }
}
