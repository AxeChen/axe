package com.mg.axe.androiddevelop.develop.mvp.view;

/**
 * Created by Administrator on 2017/2/20 0020.
 */
public interface ILoginView {
    public String getUserName();
    public String getPws();
    public void showLoading();
    public void hidLoading();
    public void toMainActiivty();
    public void showFailedError();
    public void clearUserName();
    public void clearPassword();
}
