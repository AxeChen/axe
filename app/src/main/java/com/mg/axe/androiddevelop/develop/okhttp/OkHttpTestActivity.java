package com.mg.axe.androiddevelop.develop.okhttp;

import android.app.DownloadManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.mg.axe.androiddevelop.R;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by Administrator on 2016/12/28 0028.
 */
public class OkHttpTestActivity extends AppCompatActivity {

    /*
     *这些代码只是对OKHTTP GET POST的用法简单写了代码。从而懂得OKHTTP的GET POST的基本用法
     */
    private String url = "http://g.hiphotos.baidu.com/space/pic/item/8cb1cb13495409237b0badc39258d109b3de4929.jpg";

    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.okhttptest_layout);
    }

    public void httpGetOne(View view) {
        testGet();
    }

    public void httpPostOne(View view) {
        testPost();
    }



    private void testPost() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String json = "";
                    OkHttpClient client = new OkHttpClient();
                    RequestBody body = RequestBody.create(JSON, json);
                    Request request = new Request.Builder()
                            .url(url)
                            .post(body)
                            .build();
                    Response response = client.newCall(request).execute();
                    if (response.isSuccessful()) {
                        String string = response.body().string();
                    } else {
                        throw new IOException("Unexpected code " + response);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }


    private void testGet() {

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder().url(url).build();
                    Response response = null;
                    response = client.newCall(request).execute();
                    if (response.isSuccessful()) {

                    } else {

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }).start();
    }
}
