package com.gfq.mvvmtest.mvvm_fragment;

import androidx.lifecycle.MutableLiveData;

import com.gfq.mvvmtest.APIService;
import com.gfq.mvvmtest.News;
import com.gfq.mvvmtest.mvvm_fragment.base.BaseViewModel;
import com.scwang.smartrefresh.layout.api.RefreshLayout;

import java.util.List;

/**
 * create by 高富强
 * on {2019/10/18} {17:42}
 * desctapion:
 */
public class TestViewModel extends BaseViewModel<News.ResultBean> {
    @Override
    public MutableLiveData<List<News.ResultBean>> getListLiveData() {
         super.getListLiveData();
        APIService.call(APIService.api().getWangYiNews(currentPage + "", pageSize + ""), new APIService.OnCallBack<News>() {
            @Override
            public void onSucceed(News data) {
                listLiveData.setValue(data.getResult());
            }
        });
        return listLiveData;
    }

    @Override
    public void update(RefreshLayout refreshLayout, boolean isLoadMore) {
        super.update(refreshLayout, isLoadMore);
        APIService.call(APIService.api().getWangYiNews(currentPage + "", pageSize + ""),refreshLayout,isLoadMore, new APIService.OnCallBack<News>() {
            @Override
            public void onSucceed(News data) {
                listLiveData.setValue(data.getResult());
            }
        });
    }
}
