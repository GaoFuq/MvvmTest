package com.gfq.mvvmtest.mvvm_fragment.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.gfq.mvvmtest.MyViewModel;
import com.gfq.mvvmtest.News;
import com.gfq.mvvmtest.R;
import com.gfq.mvvmtest.binding.RVBindingAdapter;
import com.gfq.mvvmtest.binding.SuperBindingViewHolder;
import com.gfq.mvvmtest.databinding.FragmentABinding;

import java.util.List;

/**
 * create by 高富强
 * on {2019/10/18} {15:28}
 * desctapion:
 */
public abstract class BaseMvvmFragment<B extends ViewDataBinding, VM > extends Fragment {
    protected VM viewModel;
    protected B binding;
    protected NavController controller;

    public abstract int layout();

    protected abstract void setViewModelObserve();

    protected abstract void bindData();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setViewModelObserve();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, layout(), container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        controller = NavHostFragment.findNavController(this);
//        setViewModelObserve();
        binding.setLifecycleOwner(this);
        bindData();
    }


}
