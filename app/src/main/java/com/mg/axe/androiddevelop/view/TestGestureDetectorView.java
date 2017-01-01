package com.mg.axe.androiddevelop.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by Administrator on 2016/11/20 0020.
 */
public class TestGestureDetectorView extends View implements View.OnClickListener {

    GestureDetector gestureDetector;

    private int sdf = 0;

    public TestGestureDetectorView(Context context) {
        this(context, null);
    }

    public TestGestureDetectorView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TestGestureDetectorView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        gestureDetector = new GestureDetector(context, new MySimpleOnGestureListener());
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        gestureDetector.onTouchEvent(event);
        return true;
    }

    @Override
    public void onClick(View v) {

    }

    private class MySimpleOnGestureListener extends SimpleOnGestureListener {

        public MySimpleOnGestureListener() {
            super();
        }

        @Override
        public boolean onDown(MotionEvent e) {
            Log.i("TestGestureDetectorView", "onDown");
            return false;
        }

        @Override
        public void onShowPress(MotionEvent e) {
            Log.i("TestGestureDetectorView", "onShowPress");
        }

        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            Log.i("TestGestureDetectorView", "onSingleTapUp");
            return false;
        }

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            Log.i("TestGestureDetectorView", "onScroll");
            return true;
        }

        @Override
        public void onLongPress(MotionEvent e) {
            Log.i("TestGestureDetectorView", "onLongPress");
        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            Log.i("TestGestureDetectorView", "onFling");
            return false;
        }

        @Override
        public boolean onSingleTapConfirmed(MotionEvent e) {
            Log.i("TestGestureDetectorView", "onSingleTapConfirmed");
            return false;
        }

        @Override
        public boolean onDoubleTap(MotionEvent e) {
            Log.i("TestGestureDetectorView", "onDoubleTap");
            return false;
        }

        @Override
        public boolean onDoubleTapEvent(MotionEvent e) {
            Log.i("TestGestureDetectorView", "onDoubleTapEvent");
            return false;
        }

        @Override
        public boolean onContextClick(MotionEvent e) {
            return super.onContextClick(e);
        }
    }


}
