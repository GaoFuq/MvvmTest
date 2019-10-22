package com.gfq.mvvmtest;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.scwang.smartrefresh.layout.api.RefreshLayout;

import java.util.ArrayList;
import java.util.List;

public class MyViewModel extends ViewModel {
    private MutableLiveData<List<News.ResultBean>> newsMutableLiveData;
    private int currentPage = 1;
    private List<News.ResultBean> list = new ArrayList<>();

    public MutableLiveData<List<News.ResultBean>> getNews() {
        if (newsMutableLiveData == null) {
            newsMutableLiveData = new MutableLiveData<>();
            newsMutableLiveData.setValue(list);
            APIService.call(APIService.api().getWangYiNews(currentPage + "", "1"), new APIService.OnCallBack<News>() {
                @Override
                public void onSucceed(News data) {
                    newsMutableLiveData.setValue(data.getResult());
                }


            });

        }
        return newsMutableLiveData;
    }


    public void update() {
        currentPage++;
        APIService.call(APIService.api().getWangYiNews(currentPage + "", "1"), new APIService.OnCallBack<News>() {
            @Override
            public void onSucceed(News data) {
                newsMutableLiveData.setValue(data.getResult());
            }


        });
    }

    public void loadMore() {
        currentPage++;
        APIService.call(APIService.api().getWangYiNews(currentPage + "", "1"), new APIService.OnCallBack<News>() {
            @Override
            public void onSucceed(News data) {
               /* List<News.ResultBean> list = newsMutableLiveData.getValue();
                list.addAll(data.getResult());
                newsMutableLiveData.setValue(list);*/
                newsMutableLiveData.setValue(data.getResult());
            }


        });
    }
    public void loadMore(RefreshLayout refreshLayout) {
        currentPage++;
        APIService.call(APIService.api().getWangYiNews(currentPage + "", "1"), new APIService.OnCallBack<News>() {
            @Override
            public void onSucceed(News data) {
               /* List<News.ResultBean> list = newsMutableLiveData.getValue();
                list.addAll(data.getResult());
                newsMutableLiveData.setValue(list);*/
                newsMutableLiveData.setValue(data.getResult());
                refreshLayout.finishLoadMore();
            }


        });
    }


    public void update(RefreshLayout refreshLayout) {
        currentPage++;
        APIService.call(APIService.api().getWangYiNews(currentPage + "", "1"), new APIService.OnCallBack<News>() {
            @Override
            public void onSucceed(News data) {
                newsMutableLiveData.setValue(data.getResult());
                refreshLayout.finishRefresh();
            }


        });
    }


    public void refresh2(RefreshLayout refreshLayout) {
        currentPage++;
        APIService.call(APIService.api().getWangYiNews(currentPage + "", "1"),refreshLayout,false ,new APIService.OnCallBack<News>() {
            @Override
            public void onSucceed(News data) {
                newsMutableLiveData.setValue(data.getResult());
            }


        });
    }

    public void loadMore2(RefreshLayout refreshLayout) {
        currentPage++;
        APIService.call(APIService.api().getWangYiNews(currentPage + "", "1"),refreshLayout,true, new APIService.OnCallBack<News>() {
            @Override
            public void onSucceed(News data) {
                newsMutableLiveData.setValue(data.getResult());
            }


        });
    }

}
