package com.mg.axe.androiddevelop.develop.mvp.bean;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.mg.axe.androiddevelop.BR;

/**
 * Created by Administrator on 2017/2/20 0020.
 */
public class UserBean extends BaseObservable {

    public static String SEX_MAN = "man";
    public static String SEX_WOMAN = "woman";

    private String userId;
    public String userName;
    private String sex;
    private String description;

    @Bindable
    public String getSex() {
        return this.sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
        notifyPropertyChanged(BR.sex);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
