package com.mg.axe.androiddevelop.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;

/**
 * Created by Administrator on 2016/11/16 0016.
 */
public class TestVelocityView extends View {


    GetVelocityListener listener;
    VelocityTracker velocityTracker;
    Paint paint = new Paint();

    public GetVelocityListener getListener() {
        return listener;
    }

    public void setListener(GetVelocityListener listener) {
        this.listener = listener;
    }

    public TestVelocityView(Context context) {
        this(context,null);
    }

    public TestVelocityView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public TestVelocityView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        paint.setTextSize(50);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        canvas.save();
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.FILL);


        canvas.drawText("x = "+xVelocity+"y ="+yVelocity,0+getLeft(),0+getTop(),paint);
        canvas.restore();
}

    int xVelocity = 0;
    int yVelocity = 0;


    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                velocityTracker = VelocityTracker.obtain();
                break;
            case MotionEvent.ACTION_MOVE:
                velocityTracker.addMovement(event);
                velocityTracker.computeCurrentVelocity(1000);
                xVelocity = (int) velocityTracker.getXVelocity();
                yVelocity = (int) velocityTracker.getYVelocity();

                if (listener != null) {
                    listener.get(xVelocity, yVelocity);
                    invalidate();
                }
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                velocityTracker.clear();
                velocityTracker.recycle();
                break;

        }


        return true;
    }

    public interface GetVelocityListener {
        public void get(int x, int y);
    }
}
