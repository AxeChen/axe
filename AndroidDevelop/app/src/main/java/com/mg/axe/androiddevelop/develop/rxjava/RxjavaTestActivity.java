package com.mg.axe.androiddevelop.develop.rxjava;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.mg.axe.androiddevelop.R;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;


/**
 * Created by Administrator on 2016/12/22 0022.
 */
public class RxjavaTestActivity extends AppCompatActivity {

    private Observer<String> stringObserver;
    private  Observable observable;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rxjava_layout);

        stringObserver = new Observer<String>() {
            @Override
            public void onCompleted() {
                Log.i("rx_java_czf", "onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                Log.i("rx_java_czf", "Error!");
            }

            @Override
            public void onNext(String s) {
                Log.i("rx_java_czf", "Item: " + s);
            }
        };

        /**
         * The first way
         */
        observable = Observable.create(new Observable.OnSubscribe<String>() {

            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext("Hello");
                subscriber.onNext("Hi");
                subscriber.onNext("Aloha");
                subscriber.onCompleted();
            }
        });
        /**
         * The second
         */
        observable = Observable.just("one","two","three");

        /**
         * The third way.
         */
        String[] words = {"wa", "ye", "crop"};
        observable = Observable.from(words);

    }

    /**
     * Button's click event.
     * it will call subscribe .
     * @param view
     */
    public void justDoIt(View view) {
//        当 Observable 被订阅的时候，OnSubscribe 的 call() 方法会自动被调用，
// 事件序列就会依照设定依次触发（对于上面的代码，就是观察者Subscriber
// 将会被调用三次 onNext() 和一次 onCompleted()）。这样，由被观察者调用了观察者的回调方法，
// 就实现了由被观察者向观察者的事件传递，即观察者模式。
        observable.subscribe(stringObserver);
    }

}
