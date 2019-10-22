package com.gfq.mvvmtest.mvvm_fragment;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gfq.mvvmtest.R;
import com.gfq.mvvmtest.databinding.FragmentCBinding;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragC extends Fragment {

    FragmentCBinding binding;

    CViewModel viewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_c, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        viewModel = ViewModelProviders.of(getActivity()).get(CViewModel.class);
        binding.setViewmodel(viewModel);
        viewModel.getBeanMutableLiveData().observe(getActivity(), new Observer<Bean>() {
            @Override
            public void onChanged(Bean bean) {
                binding.setData(bean);
            }
        });
        binding.setLifecycleOwner(getActivity());
        binding.refresh.setOnClickListener(v -> {
            viewModel.refresh();
        });
        binding.jump.setOnClickListener(v -> {
            NavHostFragment.findNavController(this).navigate(R.id.action_fragC_to_fragD);
        });
    }
}
