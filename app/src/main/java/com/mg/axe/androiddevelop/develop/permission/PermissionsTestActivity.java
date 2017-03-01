package com.mg.axe.androiddevelop.develop.permission;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.mg.axe.androiddevelop.R;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.assist.ImageSize;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;

import java.security.Permissions;


/**
 * Created by Administrator on 2017/1/3 0003.
 */
public class PermissionsTestActivity extends AppCompatActivity {

    private TextView mDescription;

    private ImageView image1;
    private ImageView image2;

    private ImageLoader imageLoader;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.permission_layout);
        image1 = (ImageView) findViewById(R.id.img1);
        image2 = (ImageView) findViewById(R.id.img2);
        mDescription = (TextView) findViewById(R.id.tv_description);
        imageLoader = ImageLoader.getInstance();
        ImageLoaderConfiguration configuration = ImageLoaderConfiguration
                .createDefault(this);
        imageLoader.init(configuration);
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
        imageLoader.loadImage("http://img1.imgtn.bdimg.com/it/u=3432556783,2172498094&fm=23&gp=0.jpg", new ImageSize(50, 50), new ImageLoadingListener() {
            @Override
            public void onLoadingStarted(String imageUri, View view) {

            }

            @Override
            public void onLoadingFailed(String imageUri, View view, FailReason failReason) {

            }

            @Override
            public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {

                image1.setImageBitmap(loadedImage);
            }

            @Override
            public void onLoadingCancelled(String imageUri, View view) {

            }
        });

//        ImageLoader.getInstance().loadImage("http://img1.imgtn.bdimg.com/it/u=3432556783,2172498094&fm=23&gp=0.jpg", new ImageSize(100, 100), new ImageLoadingListener() {
//            @Override
//            public void onLoadingStarted(String imageUri, View view) {
//
//            }
//
//            @Override
//            public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
//
//            }
//
//            @Override
//            public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
//                image2.setImageBitmap(loadedImage);
//            }
//
//            @Override
//            public void onLoadingCancelled(String imageUri, View view) {
//
//            }
//        });

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
