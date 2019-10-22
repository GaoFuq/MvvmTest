package com.gfq.mvvmtest.mvvm_fragment;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.gfq.mvvmtest.MyViewModel;
import com.gfq.mvvmtest.News;
import com.gfq.mvvmtest.R;
import com.gfq.mvvmtest.binding.RVBindingAdapter;
import com.gfq.mvvmtest.binding.SuperBindingViewHolder;
import com.gfq.mvvmtest.databinding.FragmentABinding;
import com.gfq.mvvmtest.mvvm_fragment.base.BaseMvvmFragment;

import java.util.List;

/**
 * create by 高富强
 * on {2019/10/18} {15:35}
 * desctapion:
 */
public class FragAPlus extends BaseMvvmFragment<FragmentABinding,MyViewModel> {
    RVBindingAdapter<News.ResultBean> adapter;
    @Override
    public int layout() {
        return R.layout.fragment_a;
    }

    @Override
    protected void setViewModelObserve() {
        viewModel = ViewModelProviders.of(getActivity()).get(MyViewModel.class);
        viewModel.getNews().observe(this, new Observer<List<News.ResultBean>>() {
            @Override
            public void onChanged(List<News.ResultBean> resultBeans) {
                adapter.setDataList(resultBeans);
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
        adapter.setDataList(viewModel.getNews().getValue());

        binding.rv.setAdapter(adapter);
        binding.refresh.setOnClickListener(v ->{viewModel.update();});
        binding.loadmore.setOnClickListener(v ->{viewModel.loadMore();});
        binding.jump.setOnClickListener(v->{
            controller.navigate(R.id.action_fragAPlus_to_fragB);
        });
    }
}
