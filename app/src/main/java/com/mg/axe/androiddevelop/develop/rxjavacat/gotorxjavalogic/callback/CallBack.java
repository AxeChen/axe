package com.mg.axe.androiddevelop.develop.rxjavacat.gotorxjavalogic.callback;


/**
 * Created by Administrator on 2017/2/27 0027.
 *
 * 从action.api中的回调中我们可以知道。
 * 1、他们都有一个分发结果的方法
 * 2、他们都有一个错误处理的2方法
 * 既然如此我们就把他们合并到一起，然后就是如下代码
 */

public interface CallBack<T> {
    void onResult(T res);
    void onError(Exception e);
}
