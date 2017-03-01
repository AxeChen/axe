package com.mg.axe.androiddevelop.develop.mvp.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.mg.axe.androiddevelop.R;
import com.mg.axe.androiddevelop.develop.mvp.presenter.UserLoginPresent;
import com.mg.axe.androiddevelop.develop.mvp.view.ILoginView;
import com.mg.axe.androiddevelop.develop.mvvm.activity.ProfileActivity;

/**
 * Created by Administrator on 2017/2/20 0020.
 */
public class LoginActivity extends AppCompatActivity implements ILoginView{

    private EditText userName;
    private EditText userPws;
    private Button mBtnLogin;
    private Button mBtnClean;
    private ProgressDialog progressDialog;
    private UserLoginPresent mPresent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_mvp_layout);
        initView();
    }

    private void initView(){
        userName = (EditText) findViewById(R.id.ed_name);
        userPws = (EditText) findViewById(R.id.ed_pws);
        mBtnClean = (Button) findViewById(R.id.btn_clean);
        mBtnLogin = (Button) findViewById(R.id.btn_login);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Login ...");
        progressDialog.create();

        mPresent = new UserLoginPresent(this);

        mBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresent.login();
            }
        });
    }



    @Override
    public String getUserName() {
        return userName.getText().toString();
    }

    @Override
    public String getPws() {
        return userPws.getText().toString();
    }

    @Override
    public void showLoading() {
        progressDialog.show();
    }

    @Override
    public void hidLoading() {
        progressDialog.cancel();
    }

    @Override
    public void toMainActiivty() {
        Intent intent = new Intent(LoginActivity.this,ProfileActivity.class);
        startActivity(intent);
    }

    @Override
    public void showFailedError() {
        Toast.makeText(this.getApplicationContext(),"login fail",Toast.LENGTH_LONG).show();
    }

    @Override
    public void clearUserName() {
        userName.setText("");
    }

    @Override
    public void clearPassword() {
        userPws.setText("");
    }
}
