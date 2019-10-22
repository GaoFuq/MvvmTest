package com.gfq.mvvmtest.mvvm_fragment.base;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.gfq.mvvmtest.APIService;
import com.gfq.mvvmtest.HttpApi;
import com.gfq.mvvmtest.News;
import com.scwang.smartrefresh.layout.api.RefreshLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * create by 高富强
 * on {2019/10/18} {17:23}
 * desctapion:
 */
public class BaseViewModel<T> extends ViewModel {
    protected int currentPage = 1;
    protected int pageSize = 1;
    protected MutableLiveData<T> liveData;
    protected MutableLiveData<List<T>> listLiveData;
    private boolean isLoadMore;

    public MutableLiveData<T> getLiveData() {
        isLoadMore=false;
        if(liveData==null){
            liveData = new MutableLiveData<>();
        }
        return liveData;
    }

    public MutableLiveData<List<T>> getListLiveData() {
        isLoadMore=false;
        if(listLiveData==null){
            listLiveData = new MutableLiveData<>();
            listLiveData.setValue(new ArrayList<>());
        }
        return listLiveData;
    }

    public void update(RefreshLayout refreshLayout, boolean isLoadMore){
        this.isLoadMore=isLoadMore;
        if(isLoadMore){
            currentPage++;
        }
    }


    public boolean getIsLoadMore() {
        return isLoadMore;
    }
}
