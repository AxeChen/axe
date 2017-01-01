package com.mg.axe.androiddevelop.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Administrator on 2016/10/15 0015.
 */
public class MyOnDrawView extends View {

    private Paint mPaint = new Paint();

    public MyOnDrawView(Context context) {
        this(context,null);
    }

    public MyOnDrawView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MyOnDrawView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int width = getMeasuredWidth();
        int heght = getMeasuredHeight();
        int radius = Math.min(width,heght)/2;
        mPaint.setColor(Color.BLUE);
        canvas.drawCircle(width/2,heght/2,radius,mPaint);
    }
}
