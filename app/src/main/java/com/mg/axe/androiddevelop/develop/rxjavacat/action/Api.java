package com.mg.axe.androiddevelop.develop.rxjavacat.action;

import android.net.Uri;

import com.mg.axe.androiddevelop.develop.rxjavacat.bean.Cat;

import java.net.URI;
import java.util.List;

/**
 * Created by Administrator on 2017/2/27 0027.
 */

public interface Api {
    List<Cat> queryCats(String query,CatQueryCallBack catQueryCallBack);
    Uri store(Cat cat,StoreCallback callback);

    //添加阻塞异步回调
    interface CatQueryCallBack{
        void onCatListReceived(List<Cat> cats);
        void onError(Exception e);
    }

    interface StoreCallback{
        void onCatStored(Uri uri);
        void onStoreFailed(Exception e);
    }

}
