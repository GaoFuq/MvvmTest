package com.gfq.mvvmtest;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import com.gfq.mvvmtest.binding.RVBindingAdapter;
import com.gfq.mvvmtest.binding.SuperBindingViewHolder;
import com.gfq.mvvmtest.databinding.ActivityMain2Binding;

import java.util.List;


public class MainActivity_2 extends AppCompatActivity {
    RVBindingAdapter<News.ResultBean> adapter;
    private MyViewModel viewModel;
    ActivityMain2Binding binding;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       binding = DataBindingUtil.setContentView(this,R.layout.activity_main_2);
        viewModel = ViewModelProviders.of(this).get(MyViewModel.class);
        viewModel.getNews().observe(this, new Observer<List<News.ResultBean>>() {
            @Override
            public void onChanged(List<News.ResultBean> resultBeans) {
                adapter.setDataList(resultBeans);
            }
        });
        binding.setLifecycleOwner(this);
        adapter = new RVBindingAdapter<News.ResultBean>(this, BR.news) {
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

    }



}
