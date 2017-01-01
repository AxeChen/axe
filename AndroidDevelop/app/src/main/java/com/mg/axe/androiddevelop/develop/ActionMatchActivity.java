package com.mg.axe.androiddevelop.develop;

import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.mg.axe.androiddevelop.R;

/**
 * Created by Administrator on 2016/9/24 0024.
 */
public class ActionMatchActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.match_layout);
    }

    public void matchA(View view){
        Intent intent = new Intent(Intent.ACTION_MEDIA_SHARED);
        startActivity(intent);
    }
}
