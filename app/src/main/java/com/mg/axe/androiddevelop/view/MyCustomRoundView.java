package com.mg.axe.androiddevelop.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewConfiguration;

import com.mg.axe.androiddevelop.R;

/**
 * Created by Administrator on 2016/10/30 0030.
 */
public class MyCustomRoundView extends View {

    //半径
    private int mRadius;
    //颜色
    private int mRoundColor;

    //是否有边框
    private boolean isHaveBorder = false;
    //边框颜色
    private int mBorderColor;
    //边框宽度
    private int mBordWidth;

    private int mRealWidth;

    private static final int DEFUALT_COLOR_UNREACH=0XFFD3D6DA;

    private Paint paint = new Paint();


    public MyCustomRoundView(Context context) {
        this(context,null);
    }

    public MyCustomRoundView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MyCustomRoundView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        getAttribute(attrs);

    }

    private void getAttribute(AttributeSet attrs){

        TypedArray ta = getContext().obtainStyledAttributes(attrs, R.styleable.MyCustomRoundView);
        //圆圈的相关属性获取
        mRadius = (int) ta.getDimension(R.styleable.MyCustomRoundView_myradius,0);
        mRoundColor = ta.getColor(R.styleable.MyCustomRoundView_round_color,DEFUALT_COLOR_UNREACH);

        //边框的属性获取
        mBordWidth = (int) ta.getDimension(R.styleable.MyCustomRoundView_border_width,0);
        mBorderColor = ta.getColor(R.styleable.MyCustomRoundView_border_color,DEFUALT_COLOR_UNREACH);


        mRealWidth = mRadius*2;
        rectF = new RectF(100,100,mRadius*2,mRadius*2);

        ta.recycle();
    }

    private RectF rectF;

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.save();
        canvas.translate(getPaddingLeft()+mRadius,getPaddingTop()+mRadius);


        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(mBorderColor);
        paint.setStrokeWidth(10);
        float sweepAngle = 40*1.0f/360;
        canvas.drawArc(rectF,0,360,false,paint);
        paint.setStyle(Paint.Style.FILL);
        //画一个圆
        paint.setColor(mRoundColor);
        canvas.drawCircle(0,0,mRadius,paint);

        //画个圈
//        canvas.translate(-mRadius,-mRadius);

        //
        canvas.restore();


    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    //dp px
    protected int dp2px(int dpval) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dpval, getResources().getDisplayMetrics());
    }
}
