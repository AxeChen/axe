package com.mg.axe.androiddevelop.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.ProgressBar;

import com.mg.axe.androiddevelop.R;

/**
 * Created by Administrator on 2016/9/4 0004.
 */
public class MyProgress extends ProgressBar{

    private static final int DEFUALT_TEXT_SIZE=10;
    private static final int DEFUALT_TEXT_COLOR=0xFFFC00D1;
    private static final int DEFUALT_COLOR_UNREACH=0XFFD3D6DA;
    private static final int DEFUALT_HEIGHT_UNREACH=2;
    private static final int DEFUALT_COLOR_REACH=DEFUALT_TEXT_COLOR;
    private static final int DEFUALT_HEIGHT_REACH=2;//DP
    private static final int DEFUALT_TEXT_OFFSET=10;//DP

    protected int mTextSize = sp2px(DEFUALT_TEXT_SIZE);
    protected int mTextColor = DEFUALT_TEXT_COLOR;
    protected int mUNreachColor = DEFUALT_COLOR_UNREACH;
    protected int mUnreachHeight = dp2px(DEFUALT_HEIGHT_UNREACH);
    protected int mReachColor = DEFUALT_COLOR_REACH;
    protected int mReachHeight = dp2px(DEFUALT_HEIGHT_REACH);
    protected int mTextOffset = dp2px(DEFUALT_TEXT_OFFSET);

    //绘制需要的
    protected Paint mPaint = new Paint();
    protected int mRealWidth;

    public MyProgress(Context context) {
        this(context,null);
    }

    public MyProgress(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MyProgress(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        obtainStyleAttrs(attrs);

    }

    //自定义属性的获取(第一步)
    private void obtainStyleAttrs(AttributeSet attributeSet){
        //如何获取自定义属性
        TypedArray ta = getContext().obtainStyledAttributes(attributeSet, R.styleable.MyProgress);

        mTextSize = (int) ta.getDimension(R.styleable.MyProgress_progress_text_size,mTextSize);
        mTextColor = ta.getColor(R.styleable.MyProgress_progress_text_color,mTextColor);
        mTextOffset = (int) ta.getDimension(R.styleable.MyProgress_progress_text_offset,mTextOffset);

        mUNreachColor = ta.getColor(R.styleable.MyProgress_progress_unreach_color,mUNreachColor);
        mUnreachHeight = (int) ta.getDimension(R.styleable.MyProgress_progress_unreach_height,mUnreachHeight);

        mReachColor = ta.getColor(R.styleable.MyProgress_progress_reach_color,mReachColor);
        mReachHeight = (int) ta.getDimension(R.styleable.MyProgress_progress_reach_height,mReachHeight);

        ta.recycle();

        mPaint.setTextSize(mTextSize);
    }


    //控件测量（第二部）
    @Override
    protected synchronized void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        // 三种测量模式
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthVal = MeasureSpec.getSize(widthMeasureSpec);
        int height = measureHeight(heightMeasureSpec);

        setMeasuredDimension(widthVal,height);
        mRealWidth = getMeasuredWidth() - getPaddingLeft() - getPaddingRight();
    }

    //第三步
    @Override
    protected synchronized void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //根据设计图来测试
        canvas.save();
        canvas.translate(getPaddingLeft(),getHeight()/2);

        //判断是否要绘制unreach()
        boolean noNeedUnRech = false;

        String text = getProgress()+"%";
        int textWith = (int) mPaint.measureText(text);
        //获取百分比
        float radio = getProgress()*1.0f/getMax();
        float progressX = radio*mRealWidth;

        if(progressX + textWith > mRealWidth){
            progressX = mRealWidth - textWith;
            noNeedUnRech = true;
        }


        float endx = progressX - mTextOffset/2;
        if(endx > 0){
            //绘制 reach
            mPaint.setColor(mReachColor);
            mPaint.setStrokeWidth(mReachHeight);
            canvas.drawLine(0,0,endx,0,mPaint);
        }

        //draw text
        mPaint.setColor(mTextColor);
        int y = (int) (-(mPaint.descent() + mPaint.ascent())/2);
        canvas.drawText(text,progressX,y,mPaint);

        //draw unreach bar
        if(!noNeedUnRech){
            float start = progressX + mTextOffset/2 + textWith;
            mPaint.setColor(mUNreachColor);
            mPaint.setStrokeWidth(mUnreachHeight);
            canvas.drawLine(start,0,mRealWidth,0,mPaint);
        }

        canvas.restore();
    }

    private int measureHeight(int heightMeasureSpec){
        int result = 0;
        int mode = MeasureSpec.getMode(heightMeasureSpec);
        int size = MeasureSpec.getSize(heightMeasureSpec);
        if(mode == MeasureSpec.EXACTLY){
            // 精确值 100dp match_parent
            result = size;
        }else{
            int textHeight = (int) (mPaint.descent()-mPaint.ascent());
            result = getPaddingTop() + getPaddingBottom() + Math.max(Math.max(mReachHeight,mUnreachHeight),Math.abs(textHeight));
            if(mode == MeasureSpec.AT_MOST){
                //给的只不能超过最大的值
                result = Math.min(result,size);
            }
        }
        return result;
    }

    //dp px
    protected int dp2px(int dpval){
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,dpval,getResources().getDisplayMetrics());
    }

    //sp px
    protected int sp2px(int spval){
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,spval,getResources().getDisplayMetrics());
    }
}
