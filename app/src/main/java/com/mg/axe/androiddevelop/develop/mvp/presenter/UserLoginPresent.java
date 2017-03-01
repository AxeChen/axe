package com.mg.axe.androiddevelop.develop.mvp.presenter;

import android.os.Handler;

import com.mg.axe.androiddevelop.develop.mvp.action.UserLoginAction;
import com.mg.axe.androiddevelop.develop.mvp.view.ILoginView;

/**
 * Created by Administrator on 2017/2/20 0020.
 */
public class UserLoginPresent {

    private UserLoginAction userLoginAction;
    private ILoginView mLoginView;
    private Handler mHandler = new Handler();

    public UserLoginPresent(ILoginView loginView){
        userLoginAction = new UserLoginAction();
        this.mLoginView = loginView;
    }

    public void login(){

        mLoginView.showLoading();
        userLoginAction.login(mLoginView.getUserName(), mLoginView.getPws(), new UserLoginAction.LoginResultListener() {
            @Override
            public void loginSuccess() {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        mLoginView.hidLoading();
                        mLoginView.toMainActiivty();
                    }
                });

            }

            @Override
            public void loginFail() {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        mLoginView.hidLoading();
                        mLoginView.showFailedError();
                    }
                });

            }
        });

    }

}
