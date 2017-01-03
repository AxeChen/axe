package com.mg.axe.androiddevelop.develop.permission;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Administrator on 2017/1/3 0003.
 */
public class BaseActivity extends AppCompatActivity {

    //一个基类 对权限的统一管理


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /**
     * 对单个权限做判断
     * 如果return true则有权限
     * @param permission
     * @param requestCode
     * @return
     */
    public boolean checkPermission(String permission ,int requestCode) {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{permission}, requestCode);
            return false;
        }
        return true;
    }
}
