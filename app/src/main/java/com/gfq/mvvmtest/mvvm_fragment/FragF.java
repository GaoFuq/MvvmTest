package com.gfq.mvvmtest.mvvm_fragment;

import androidx.annotation.NonNull;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.gfq.mvvmtest.MyViewModel;
import com.gfq.mvvmtest.News;
import com.gfq.mvvmtest.R;
import com.gfq.mvvmtest.binding.RVBindingAdapter;
import com.gfq.mvvmtest.binding.SuperBindingViewHolder;
import com.gfq.mvvmtest.databinding.FragmentABinding;
import com.gfq.mvvmtest.mvvm_fragment.TestViewModel;
import com.gfq.mvvmtest.mvvm_fragment.base.BaseMvvmFragment;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.List;

/**
 * create by 高富强
 * on {2019/10/18} {17:50}
 * desctapion:
 */
public class FragF extends BaseMvvmFragment<FragmentABinding, TestViewModel> {
    RVBindingAdapter<News.ResultBean> adapter;

    @Override
    public int layout() {
        return R.layout.fragment_a;
    }

    @Override
    protected void setViewModelObserve() {
        viewModel = ViewModelProviders.of(getActivity()).get(TestViewModel.class);
        viewModel.getListLiveData().observe(this, new Observer<List<News.ResultBean>>() {
            @Override
            public void onChanged(List<News.ResultBean> resultBeans) {
                if (viewModel.getIsLoadMore()) {
                    adapter.addAll(resultBeans);
                } else {
                    adapter.setDataList(resultBeans);
                }
            }
        });
    }

    @Override
    protected void bindData() {
        adapter = new RVBindingAdapter<News.ResultBean>(getActivity(), com.gfq.mvvmtest.BR.news) {
            @Override
            public void setPresentor(SuperBindingViewHolder holder, int position) {

            }

            @Override
            public int getLayoutId() {
                return R.layout.activity_item2;
            }
        };
        adapter.setDataList(viewModel.getListLiveData().getValue());
        binding.rv.setAdapter(adapter);
        binding.smartRefreshLayout.setRefreshHeader(new ClassicsHeader(getContext()));
        binding.smartRefreshLayout.setRefreshFooter(new ClassicsFooter(getContext()));
        binding.smartRefreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
             viewModel.update(refreshLayout,true);

            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                viewModel.update(refreshLayout,false);
            }
        });


    }
}

