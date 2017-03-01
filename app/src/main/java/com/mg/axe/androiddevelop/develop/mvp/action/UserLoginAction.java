package com.mg.axe.androiddevelop.develop.mvp.action;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;

import java.lang.ref.WeakReference;

/**
 * Created by Administrator on 2017/2/20 0020.
 */
public class UserLoginAction implements Ilogin {

    public static LoginResultListener mCallBack;


    @Override
    public void login(final String name, final String pws) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                }catch (Exception e){
                    e.printStackTrace();
                }////////////////////////////////////////////

                if(mCallBack == null){
                    return;
                }
                if("admin".equals(name) && "123321".equals(pws)){
                    mCallBack.loginSuccess();
                }else{
                    mCallBack.loginFail();
                }
            }
        }).start();
    }

    public void login(String name, String pws,LoginResultListener loginResultListener){
        mCallBack = loginResultListener;
        login(name,pws);
    }

    public interface LoginResultListener{
        public void loginSuccess();
        public void loginFail();
    }

}
