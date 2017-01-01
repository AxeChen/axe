package com.mg.axe.androiddevelop.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;

/**
 * Created by Administrator on 2016/10/10 0010.
 */
public class TestMesureView extends View {

    public TestMesureView(Context context) {
        this(context, null);
    }

    public TestMesureView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TestMesureView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = 0;
        int height = 0;

        int widthMode = getMode(widthMeasureSpec);
        int heightMode = getMode(heightMeasureSpec);

        /** 测量width **/
        width = getReallySize(widthMode,widthMeasureSpec);
        /** 测量height **/
        height = getReallySize(heightMode,heightMeasureSpec);

        Log.i("really width mode",logMode(widthMode));
        Log.i("really width",String.valueOf(width));

        Log.i("really split","---------------------------");

        Log.i("really height mode",logMode(heightMode));
        Log.i("really height",String.valueOf(height));

        setMeasuredDimension(width, height);
    }



    /**
     * 获取测量模式
     * @param sizeMeasureSpec
     * @return
     */
    private int getMode(int sizeMeasureSpec){
        return MeasureSpec.getMode(sizeMeasureSpec);
    }

    /**
     * 通过测量模式获取真正的Size
     * @param mode
     * @param sizeMeasureSpec
     * @return
     */
    private int getReallySize(int mode,int sizeMeasureSpec){
        int specSize = 0;
        switch (mode){
            case MeasureSpec.AT_MOST:
            case MeasureSpec.EXACTLY:
                specSize = MeasureSpec.getSize(sizeMeasureSpec);
                break;
            case MeasureSpec.UNSPECIFIED:
                specSize = sizeMeasureSpec;
                break;
        }
        return specSize;
    }

    /**
     * Log mode
     * @param mode
     * @return
     */
    private String logMode(int mode){
        switch (mode){
            case MeasureSpec.AT_MOST:
                return "AT_MOST";
            case MeasureSpec.EXACTLY:
                return "EXACTLY";
            case MeasureSpec.UNSPECIFIED:
                return "UNSPECIFIED";
        }
        return "";
    }

}
