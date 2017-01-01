package com.mg.axe.androiddevelop.develop;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.mg.axe.androiddevelop.R;
import com.mg.axe.androiddevelop.view.TestVelocityView;

/**
 * Created by Administrator on 2016/9/17 0017.
 */
public class TestVelocityActivity extends AppCompatActivity implements TestVelocityView.GetVelocityListener {


    private TestVelocityView mTestView;
    private TextView mTvInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.testb_layout);
        mTvInfo = (TextView) findViewById(R.id.tv_getinfo);
        mTestView = (TestVelocityView) findViewById(R.id.testview);
        mTestView.setListener(this);
    }


    @Override
    public void get(int x, int y) {
        mTvInfo.setText("x = "+x+" y="+y);
    }
}
