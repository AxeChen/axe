package com.mg.axe.androiddevelop.develop.rxjavacat.logic;

import android.net.Uri;

import com.mg.axe.androiddevelop.develop.rxjavacat.action.ApiWrapper;
import com.mg.axe.androiddevelop.develop.rxjavacat.bean.Cat;
import com.mg.axe.androiddevelop.develop.rxjavacat.gotorxjavalogic.callback.AsyncJob;
import com.mg.axe.androiddevelop.develop.rxjavacat.gotorxjavalogic.callback.CallBack;

import java.util.Collections;
import java.util.List;

/**
 * Created by Administrator on 2017/2/27 0027.
 * 传入一个顶级的CsllBack来减少回调层数
 *
 */

public class CatgotoHelpter {

    ApiWrapper apiWrapper;

    public void saveTheCutestCat(String query, final CallBack<Uri> cutcallback){
        apiWrapper.queryCats(query, new CallBack<List<Cat>>() {
            @Override
            public void onResult(List<Cat> res) {
                Cat cutest =  findCutest(res);
                apiWrapper.store(cutest,cutcallback);
            }

            @Override
            public void onError(Exception e) {
                cutcallback.onError(e);
            }
        });
    }

    public AsyncJob<Uri> saveTheCutestCat(final String query){
        return new AsyncJob<Uri>() {
            @Override
            public void start(final CallBack<Uri> callBack) {
                apiWrapper.queryCats(query).start(new CallBack<List<Cat>>() {
                    @Override
                    public void onResult(List<Cat> res) {
                        Cat cat = findCutest(res);
                        apiWrapper.store(cat).start(new CallBack<Uri>() {
                            @Override
                            public void onResult(Uri res) {
                                callBack.onResult(res);
                            }

                            @Override
                            public void onError(Exception e) {
                                callBack.onError(e);
                            }
                        });
                    }

                    @Override
                    public void onError(Exception e) {
                        callBack.onError(e);
                    }
                });
            }
        };
    }

    // 这种方法会增加代码量，但是，我们可以看到逻辑会更清晰
    public AsyncJob<Uri> saveTheCutestCatTeo(final String query){
        //1、通过 apiWrapper.queryCats 获取 AsyncJob<List<Cat>>
        final AsyncJob<List<Cat>> catsListAsyncJob = apiWrapper.queryCats(query);
        //2、通过AsyncJob的start方法获取List<Cat>，然后通过findcutest方法获取Cat，获取到AsynJob<Cat>
        final AsyncJob<Cat> catAsyncJob = new AsyncJob<Cat>() {
            @Override
            public void start(final CallBack<Cat> callBack) {
                catsListAsyncJob.start(new CallBack<List<Cat>>() {
                    @Override
                    public void onResult(List<Cat> res) {
                        callBack.onResult(findCutest(res));
                    }

                    @Override
                    public void onError(Exception e) {
                        callBack.onError(e);
                    }
                });
            }
        };

        //通过获取到的 catAsyncJob 的start方法获取到每一个cat。 然后通过apiWrapper.store获取到uri，
        // 返回一个AsyncJob<Uri> 调用者 通过 uriAsyncJob.start方法就可以获取到Uri对象啦。是不是很吊呢？
        AsyncJob<Uri> uriAsyncJob = new AsyncJob<Uri>() {
            @Override
            public void start(final CallBack<Uri> callBack) {
                catAsyncJob.start(new CallBack<Cat>() {
                    @Override
                    public void onResult(Cat res) {
                        apiWrapper.store(res, new CallBack<Uri>() {
                            @Override
                            public void onResult(Uri res) {
                                callBack.onResult(res);
                            }

                            @Override
                            public void onError(Exception e) {
                                callBack.onError(e);
                            }
                        });
                    }

                    @Override
                    public void onError(Exception e) {
                        callBack.onError(e);
                    }
                });
            }
        };
        return uriAsyncJob;
    }


    /*
     * 接下来看段代码：
     *  final AsyncJob<Cat> catAsyncJob = new AsyncJob<Cat>() {
            @Override
            public void start(final CallBack<Cat> callBack) {
                catsListAsyncJob.start(new CallBack<List<Cat>>() {
                    @Override
                    public void onResult(List<Cat> res) {
                        callBack.onResult(findCutest(res));
                    }

                    @Override
                    public void onError(Exception e) {
                        callBack.onError(e);
                    }
                });
            }
        };
     * 这里只有一行代码有点用：其他都是错误逻辑处理。
     * 我们可以把其移动到其它地方而不影响编写我们真正需要的业务代码。
     * ok 又要开始装逼了！
     */

    private Cat findCutest(List<Cat> cats) {
        return Collections.max(cats);
    }
}
