package com.mg.axe.androiddevelop.develop.rxjavacat.gotorxjavalogic.callback;

import com.mg.axe.androiddevelop.develop.rxjavacat.nearrxjava.Func;

/**
 * Created by Administrator on 2017/2/27 0027.
 * ：任何异步操作需要携带所需的常规参数和一个回调实例对象。
 * 如果我们试图去分开这几个阶段，
 * 每个异步操作仅仅将会携带一个参数对象，
 * 然后返回一些携带着回调（信息）的临时对象。
 */

public abstract class AsyncJob<T> {
    public abstract void start(CallBack<T> callBack);

    public <R> AsyncJob<R> map(final Func<T,R> func){
        final AsyncJob<T> tAsyncJob = this;
        return new AsyncJob<R>() {
            @Override
            public void start(final CallBack<R> callBack) {
                tAsyncJob.start(new CallBack<T>() {
                    @Override
                    public void onResult(T res) {
                        R r = func.call(res);
                        callBack.onResult(r);
                    }

                    @Override
                    public void onError(Exception e) {
                        callBack.onError(e);
                    }
                });
            }
        };
    }

    public <R> AsyncJob<R> flatMap(final Func<T, AsyncJob<R>> func){
        final AsyncJob<T> source = this;
        return new AsyncJob<R>() {
            @Override
            public void start(final CallBack<R> callback) {
                source.start(new CallBack<T>() {
                    @Override
                    public void onResult(T result) {
                        AsyncJob<R> mapped = func.call(result);
                        mapped.start(new CallBack<R>() {
                            @Override
                            public void onResult(R result) {
                                callback.onResult(result);
                            }

                            @Override
                            public void onError(Exception e) {
                                callback.onError(e);
                            }
                        });
                    }

                    @Override
                    public void onError(Exception e) {
                        callback.onError(e);
                    }
                });
            }
        };
    }
}
