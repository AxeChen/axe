package com.mg.axe.androiddevelop.develop.rxjavacat.logic;

import android.net.Uri;

import com.mg.axe.androiddevelop.develop.rxjavacat.action.ApiWrapper;
import com.mg.axe.androiddevelop.develop.rxjavacat.bean.Cat;
import com.mg.axe.androiddevelop.develop.rxjavacat.gotorxjavalogic.callback.AsyncJob;
import com.mg.axe.androiddevelop.develop.rxjavacat.gotorxjavalogic.callback.CallBack;
import com.mg.axe.androiddevelop.develop.rxjavacat.nearrxjava.Func;

import java.util.Collections;
import java.util.List;

/**
 * Created by Administrator on 2017/2/28 0028.
 */

public class CatgotooHelper {
    ApiWrapper apiWrapper;

    public AsyncJob<Uri> saveTheCutestCat(String query){
        AsyncJob<List<Cat>> catsListAsyncJob = apiWrapper.queryCats(query);
        final AsyncJob<Cat> catAsyncJob = catsListAsyncJob.map(new Func<List<Cat>, Cat>() {
            @Override
            public Cat call(List<Cat> cats) {
                return findCutest(cats);
            }
        });

        AsyncJob<Uri> uriAsyncJob = catAsyncJob.flatMap(new Func<Cat, AsyncJob<Uri>>() {
            @Override
            public AsyncJob<Uri> call(Cat cat) {
                return apiWrapper.store(cat);
            }
        });



        return uriAsyncJob;
    }

    private Cat findCutest(List<Cat> cats) {
        return Collections.max(cats);
    }

}
