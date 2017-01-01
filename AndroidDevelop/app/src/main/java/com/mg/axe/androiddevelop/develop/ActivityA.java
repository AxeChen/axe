package com.mg.axe.androiddevelop.develop;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;

import com.mg.axe.androiddevelop.R;

import java.util.Date;


/**
 * Created by Administrator on 2016/9/17 0017.
 */
public class ActivityA extends Activity {

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.i("axe.mg","onSaveInstanceState");
        outState.putString("key","value_string");
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        String str =   savedInstanceState.getString("key");
        String onRestoreInstanceState = "onRestoreInstanceState";
        Log.i("axe.mg", onRestoreInstanceState);
        Log.i("axe.mg","get value:"+str);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_layout);
        Log.i("axe.mg","onCreate()");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i("axe.mg","onRestart");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("axe.mg","onStart()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("axe.mg","onResume()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("axe.mg","onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("axe.mg","onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("axe.mg","onDestroy()");
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        Log.i("axe.mg","onConfigurationChanged");
    }


    public void test(Date date){
    }

}
