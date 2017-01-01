package com.mg.axe.androiddevelop.develop.rxjava;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by Administrator on 2016/12/26 0026.
 */
public class RxjavaLiftTestActivity extends AppCompatActivity{


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        testLift();
    }

    private void testLift(){

        Observable.just(0).lift(new Observable.Operator<String, Integer>() {
            @Override
            public Subscriber<? super Integer> call(Subscriber<? super String> subscriber) {
                return null;
            }
        }).subscribe();
    }

}
