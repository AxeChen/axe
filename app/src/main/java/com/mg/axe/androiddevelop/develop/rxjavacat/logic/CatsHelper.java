package com.mg.axe.androiddevelop.develop.rxjavacat.logic;

import android.net.Uri;

import com.mg.axe.androiddevelop.develop.rxjavacat.action.Api;
import com.mg.axe.androiddevelop.develop.rxjavacat.bean.Cat;

import java.util.Collections;
import java.util.List;

/**
 * Created by Administrator on 2017/2/27 0027.
 */

public class CatsHelper {
    Api api;


    public void saveTheCutestCat(String query, final CutestCatCallBack cutestCatCallBack){

        // 没有加入阻塞时
/*        try {
            List<Cat> cats = api.queryCats(query);
            Cat cutest = findCutest(cats);
            Uri saveUri = api.store(cutest);
            return saveUri;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        */
        // 加入阻塞 普通的 做法
/*        api.queryCats(query, new Api.CatQueryCallBack() {
            @Override
            public void onCatListReceived(List<Cat> cats) {
                Cat cutest = findCutest(cats);
                Uri saveUri = api.store(cutest);
                cutestCatCallBack.onCutestCatSaved(saveUri);
            }

            @Override
            public void onError(Exception e) {
                cutestCatCallBack.onQueryFailed(e);
            }
        });*/
        // 加强阻塞 普通的 做法

        api.queryCats(query, new Api.CatQueryCallBack() {
            @Override
            public void onCatListReceived(List<Cat> cats) {
                Cat cutest = findCutest(cats);
                Uri saveUri = api.store(cutest, new Api.StoreCallback() {
                    @Override
                    public void onCatStored(Uri uri) {
                        cutestCatCallBack.onCutestCatSaved(uri);
                    }

                    @Override
                    public void onStoreFailed(Exception e) {
                        cutestCatCallBack.onQueryFailed(e);
                    }
                });
                cutestCatCallBack.onCutestCatSaved(saveUri);
            }

            @Override
            public void onError(Exception e) {
                cutestCatCallBack.onQueryFailed(e);
            }
        });
        //加入多个阻塞回调之后，代码变得复杂。 如果不发生改变。这样的代码会越来越复杂！
        //怎样去解决这个问题呢， 我们建一个新包解决这个问题

    }

    private Cat findCutest(List<Cat> cats){
        return Collections.max(cats);
    }

    public interface CutestCatCallBack{
        void onCutestCatSaved(Uri uri);
        void onQueryFailed(Exception e);
    }


}
