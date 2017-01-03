package com.mg.axe.androiddevelop.develop.permission;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.mg.axe.androiddevelop.R;

import java.security.Permissions;


/**
 * Created by Administrator on 2017/1/3 0003.
 */
public class PermissionsTestActivity extends AppCompatActivity {

    private TextView mDescription;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.permission_layout);
        mDescription = (TextView) findViewById(R.id.tv_description);
        setmDescription();
    }

    private void setmDescription(){
        String str = "Android6.0 添加了权限的机制，对于一些危险的权限，需要用户给予权限，\n"+
                "核心的API：\n"+
                "ContextCompact.checkSelfPermission:用于检测是否拥有权限\n"+
                "ActivityCompat.requestPermissions():请求该权限\n"+
                "onRequestPermissionResult()：获取权限请求回调\n"+
                "\n";
        mDescription.setText(str);
    }


    public void doCall(View view){
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE)!= PackageManager.PERMISSION_GRANTED){
            //做权限处理
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.CALL_PHONE},1);
        } else {

            new Thread(new Runnable() {
                @Override
                public void run() {
                    doCall();
                }
            }).start();
        }
    }

    public void uploadFile(View view){
        if(ContextCompat.checkSelfPermission(this,Manifest.permission.WRITE_EXTERNAL_STORAGE)!=PackageManager.PERMISSION_GRANTED){

            // 处理申请权限
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
        }else{
            // uploadfile
        }
    }

    private void doCall(){
        Intent intent = new Intent(Intent.ACTION_CALL);
        Uri data = Uri.parse("15601953341");
        intent.setData(data);
        startActivity(intent);
    }

    private void uploadFile(){
        // do upload file
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case 1:
                if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    doCall();
                }else{
                    // 这里应该跳转到app info 界面
                }
                break;
            case 2:
                if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    uploadFile();
                }else{
                    // 这里应该跳转到app info 界面
                }
                break;
        }
    }
}
