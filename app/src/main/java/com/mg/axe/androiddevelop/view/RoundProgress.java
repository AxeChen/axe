package com.mg.axe.androiddevelop.view;

import android.app.Notification;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;

import com.mg.axe.androiddevelop.R;

/**
 * Created by Administrator on 2016/9/5 0005.
 */
public class RoundProgress extends MyProgress {

    private int mRadius = dp2px(30);

    private int mMaxPaintWidth ;


    public RoundProgress(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public RoundProgress(Context context) {
        this(context,null);
    }
    public RoundProgress(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mReachHeight = (int) (mUnreachHeight*2.5);
        TypedArray ta = getContext().obtainStyledAttributes(attrs, R.styleable.RoundProgress);
        mRadius = (int) ta.getDimension(R.styleable.RoundProgress_radius,mRadius);
        ta.recycle();

        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
    }

    @Override
    protected synchronized void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        mMaxPaintWidth = Math.max(mReachHeight,mUnreachHeight);
        //默认padding一致
        int expect = mRadius*2 + mMaxPaintWidth + getPaddingRight()+getPaddingLeft();
        //resolveSize 针对三种不同的绘制模式
        int width = resolveSize(expect,widthMeasureSpec);
        int height = resolveSize(expect,heightMeasureSpec);

        int readWidth = Math.min(width,height);

        mRadius = (readWidth-getPaddingLeft()-getPaddingRight()-mMaxPaintWidth)/2;
        rectF = new RectF(0,0,mRadius*2,mRadius*2);
        setMeasuredDimension(readWidth,readWidth);
    }

    private RectF rectF;

    @Override
    protected synchronized void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        String text = getProgress() + "%";
        float textWidth = mPaint.measureText(text);
        float textHeight = (mPaint.descent()+mPaint.ascent())/2;

        canvas.save();

        //移动画板
        canvas.translate(getPaddingLeft()+mMaxPaintWidth/2,getPaddingTop()+mMaxPaintWidth/2);
        mPaint.setStyle(Paint.Style.STROKE);

        //画unreach bar 就是画一个圆
        mPaint.setColor(mUNreachColor);
        mPaint.setStrokeWidth(mUnreachHeight);
        canvas.drawCircle(mRadius,mRadius,mRadius,mPaint);

        //画reach bar 画条弧线
        mPaint.setColor(mReachColor);
        mPaint.setStrokeWidth(mReachHeight);
        //计算弧度
        float sweepAngle = getProgress()*1.0f/getMax()*360;
        canvas.drawArc(rectF,0,sweepAngle,false,mPaint);

        //画文字
        mPaint.setColor(mTextColor);
        mPaint.setStyle(Paint.Style.FILL);
        canvas.drawText(text,mRadius-textWidth/2,mRadius-textHeight,mPaint);

        canvas.restore();
    }
}
