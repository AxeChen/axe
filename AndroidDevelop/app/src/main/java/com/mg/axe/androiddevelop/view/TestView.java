package com.mg.axe.androiddevelop.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;

import com.mg.axe.androiddevelop.R;

/**
 * Created by Administrator on 2016/10/8 0008.
 */
public class TestView extends View{
    public TestView(Context context) {
        this(context,null);
    }

    public TestView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public TestView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray ta = getContext().obtainStyledAttributes(attrs, R.styleable.TestView);
        float attrone = ta.getDimension(R.styleable.TestView_attrone,0);
        Log.i("attrone's value",String.valueOf(attrone));
        String attrTwo = ta.getString(R.styleable.TestView_attrtwo);
        Log.i("attrTwo's value",attrTwo);

        //测试代码
        Log.i("attr's value",dp2px(10)+"");
    }

    protected int dp2px(int dpval){
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,dpval,getResources().getDisplayMetrics());
    }
}
