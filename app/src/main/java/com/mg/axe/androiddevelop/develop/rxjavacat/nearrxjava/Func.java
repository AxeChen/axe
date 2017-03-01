package com.mg.axe.androiddevelop.develop.rxjavacat.nearrxjava;

/**
 * Created by Administrator on 2017/2/28 0028.
 * 我们该怎么写呢？我们必须做下面的两件事情：

 AsyncJob是我们转换的结果

 转换方法

 */

public interface Func<T,R> {
    R call(T t);
}
