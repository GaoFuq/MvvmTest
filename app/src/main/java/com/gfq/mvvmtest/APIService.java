package com.gfq.mvvmtest;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.scwang.smartrefresh.layout.api.RefreshLayout;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;



//APIService.executeMethod(APIService.createApi(HttpApi.class).registerUser(apikey, "test01", "123456"), new APIService.OnCallBack<RespUserInfo>() {
//            @Override
//            public void onSucceed(RespUserInfo data) {
//                Log.d("gggg", "onSucceed: ");
//            }
//
//            @Override
//            public void onCompleted() {
//
//            }
//
//            @Override
//            public void onError(Throwable e) {
//
//            }
//        });

public class APIService {
    public static final String BASE_URL = "https://api.apiopen.top/";
    private static Retrofit retrofit;
    private static HttpApi httpApi;

    static {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        retrofit = new Retrofit.Builder()
                .client(new OkHttpClient.Builder().addInterceptor(loggingInterceptor).build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(BASE_URL)
                .build();
        httpApi = retrofit.create(HttpApi.class);
    }


    public static HttpApi api (){
        return httpApi;
    }
  //  public static <T> T createApi(Class<T> clazz) {
   //     return retrofit.create(clazz);
   // }
    //HttpApi httpApi = retrofit.create(HttpApi.class);


    public static <T> void call(Observable<T> observable, final OnCallBack<T> onCallBack) {
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<T>() {
                    @Override
                    public void onNext(T o) {
                        onCallBack.onSucceed(o);
                    }

                    @Override
                    public void onError(Throwable e) {
//                        onCallBack.onError(e);
                    }

                    @Override
                    public void onComplete() {
                       // onCallBack.onCompleted();
                    }
                });

    }

    public static<T> void call(Observable<T> observable, RefreshLayout refreshLayout,boolean isLoadMore, OnCallBack<T> onCallBack) {
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<T>() {
                    @Override
                    public void onNext(T o) {
                        onCallBack.onSucceed(o);
                        if(isLoadMore){
                            refreshLayout.finishLoadMore();
                        }else {
                            refreshLayout.finishRefresh();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        //onCallBack.onError(e);
                        if(isLoadMore){
                            refreshLayout.finishLoadMore(false);
                        }else {
                            refreshLayout.finishRefresh(false);
                        }
                    }

                    @Override
                    public void onComplete() {
                        // onCallBack.onCompleted();
                    }
                });

    }

    public interface OnCallBack<T> {
        void onSucceed(T data);



    }


}
