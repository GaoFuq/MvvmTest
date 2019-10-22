package com.gfq.mvvmtest.mvvm_fragment;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gfq.mvvmtest.MyViewModel;
import com.gfq.mvvmtest.News;
import com.gfq.mvvmtest.R;
import com.gfq.mvvmtest.binding.RVBindingAdapter;
import com.gfq.mvvmtest.binding.SuperBindingViewHolder;
import com.gfq.mvvmtest.databinding.FragmentABinding;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragA extends Fragment {
    RVBindingAdapter<News.ResultBean> adapter;
    private MyViewModel viewModel;
    FragmentABinding binding;
    private NavController controller;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_a, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        controller = NavHostFragment.findNavController(this);
        viewModel = ViewModelProviders.of(getActivity()).get(MyViewModel.class);
        viewModel.getNews().observe(this, new Observer<List<News.ResultBean>>() {
            @Override
            public void onChanged(List<News.ResultBean> resultBeans) {
                adapter.setDataList(resultBeans);
            }
        });
        binding.setLifecycleOwner(this);
        adapter = new RVBindingAdapter<News.ResultBean>(getActivity(), com.gfq.mvvmtest.BR.news) {
            @Override
            public void setPresentor(SuperBindingViewHolder holder, int position) {

            }

            @Override
            public int getLayoutId() {
                return R.layout.activity_item2;
            }
        };
        adapter.setDataList(viewModel.getNews().getValue());
      /*  APIService.call(APIService.api().getWangYiNews(1+"", "5"), new APIService.OnCallBack<News>() {
            @Override
            public void onSucceed(News data) {
                adapter.setDataList(data.getResult());
            }
        });*/
        binding.rv.setAdapter(adapter);
        binding.refresh.setOnClickListener(v ->{viewModel.update();});
        binding.loadmore.setOnClickListener(v ->{viewModel.loadMore();});
        binding.jump.setOnClickListener(v->{
            controller.navigate(R.id.action_fragA_to_fragB);
        });



    }
}
