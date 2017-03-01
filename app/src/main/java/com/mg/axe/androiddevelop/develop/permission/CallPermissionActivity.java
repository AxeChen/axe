package com.mg.axe.androiddevelop.develop.permission;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.mg.axe.androiddevelop.R;

/**
 * Created by Administrator on 2017/3/1 0001.
 */

public class CallPermissionActivity extends AppCompatActivity {

    public final static int PERMISSION_CALL = 1;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.call);
    }

    public void call(View view){
        call();
    }

    private void call(){
        //1、判断是否有打电话的权限
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE)!= PackageManager.PERMISSION_GRANTED){
            //没有获取权限则做权限处理
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.CALL_PHONE},PERMISSION_CALL);
        } else {
            //已经获取了权限，则执行打电话操作
            Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:"+100010));
            startActivity(intent);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case PERMISSION_CALL:
                if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    call();
                }else{
                    //用户没有授予权限，
                }
                break;
        }
    }
}
