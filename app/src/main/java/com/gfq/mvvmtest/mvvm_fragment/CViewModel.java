package com.gfq.mvvmtest.mvvm_fragment;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

/**
 * create by 高富强
 * on {2019/10/18} {14:47}
 * desctapion:
 */
public class CViewModel extends ViewModel {
    private MutableLiveData<Bean> beanMutableLiveData;
    private int count=1;
    Bean bean = new Bean();

    public MutableLiveData<Bean> getBeanMutableLiveData() {
        if(beanMutableLiveData==null){
            beanMutableLiveData=new MutableLiveData<>();
            bean.setName("bean1");
            beanMutableLiveData.setValue(bean);
        }
        return beanMutableLiveData;
    }

    public void refresh() {
        count++;
        bean.setName("更新 "+count);
        if(beanMutableLiveData==null){
            beanMutableLiveData=new MutableLiveData<>();
        }
        beanMutableLiveData.setValue(bean);
    }
}
