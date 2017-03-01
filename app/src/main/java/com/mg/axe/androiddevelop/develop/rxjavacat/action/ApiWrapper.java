package com.mg.axe.androiddevelop.develop.rxjavacat.action;

import android.net.Uri;

import com.mg.axe.androiddevelop.develop.rxjavacat.bean.Cat;
import com.mg.axe.androiddevelop.develop.rxjavacat.gotorxjavalogic.callback.AsyncJob;
import com.mg.axe.androiddevelop.develop.rxjavacat.gotorxjavalogic.callback.CallBack;

import java.util.List;

/**
 * Created by Administrator on 2017/2/27 0027.
 * 然后修改一下调用的方法
 */


public class ApiWrapper {
    Api api;
    public void queryCats(String query, final CallBack<List<Cat>> listCallBack){
        api.queryCats(query, new Api.CatQueryCallBack() {
            @Override
            public void onCatListReceived(List<Cat> cats) {
                listCallBack.onResult(cats);
            }

            @Override
            public void onError(Exception e) {
                listCallBack.onError(e);
            }
        });
    }

    public void store(Cat cat, final CallBack<Uri> uriCallBack){
        api.store(cat, new Api.StoreCallback() {
            @Override
            public void onCatStored(Uri uri) {
                uriCallBack.onResult(uri);
            }

            @Override
            public void onStoreFailed(Exception e) {
                uriCallBack.onError(e);
            }
        });
    }

    // 使用AsyncJob后的情况，我们需要返回一个AsyncJob对象

    public AsyncJob<List<Cat>> queryCats(final String query){
        return new AsyncJob<List<Cat>>() {
            @Override
            public void start(final CallBack<List<Cat>> callBack) {
                api.queryCats(query, new Api.CatQueryCallBack() {
                    @Override
                    public void onCatListReceived(List<Cat> cats) {
                        callBack.onResult(cats);
                    }

                    @Override
                    public void onError(Exception e) {
                        callBack.onError(e);
                    }
                });
            }
        };
    }

    public AsyncJob<Uri> store(final Cat cat){
        return new AsyncJob<Uri>() {
            @Override
            public void start(final CallBack<Uri> callBack) {
                api.store(cat, new Api.StoreCallback() {
                    @Override
                    public void onCatStored(Uri uri) {
                        callBack.onResult(uri);
                    }

                    @Override
                    public void onStoreFailed(Exception e) {
                        callBack.onError(e);
                    }
                });
            }
        };
    }




}
