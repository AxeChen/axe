package com.mg.axe.androiddevelop.develop.mvvm.activity;

import android.databinding.DataBindingComponent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.mg.axe.androiddevelop.BR;
import com.mg.axe.androiddevelop.R;
import com.mg.axe.androiddevelop.develop.mvp.bean.UserBean;

/**
 * Created by Administrator on 2017/2/20 0020.
 */
public class ProfileActivity extends AppCompatActivity{

    private ViewDataBinding dataBindingUtil;
    private UserBean user;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dataBindingUtil   = DataBindingUtil.setContentView(this, R.layout.profile_layout);
        bindData();
    }

    private void bindData(){
        user = new UserBean();
        user.setSex(UserBean.SEX_MAN);
        user.setDescription("装逼如风，长伴我身！");
        user.setUserId("123321");
        user.setUserName("yasso");
        dataBindingUtil.setVariable(BR.user,user);
    }

    public void change(View view){
        user.setSex(UserBean.SEX_WOMAN);
    }
}
