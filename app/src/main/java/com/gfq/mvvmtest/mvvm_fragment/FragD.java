package com.gfq.mvvmtest.mvvm_fragment;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.gfq.mvvmtest.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragD extends Fragment {




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_d, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        TextView textView = view.findViewById(R.id.text);
        CViewModel viewModel = ViewModelProviders.of(getActivity()).get(CViewModel.class);
        viewModel.getBeanMutableLiveData().observe(getActivity(), new Observer<Bean>() {
            @Override
            public void onChanged(Bean bean) {
               textView.setText(bean.getName());
            }
        });
        viewModel.refresh();
    }
}
