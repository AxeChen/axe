package com.mg.axe.androiddevelop.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/9/6 0006.
 */
public class FlowLayout extends ViewGroup {

    //new 时 调用
    public FlowLayout(Context context) {
        this(context,null);
    }

    //布局文件 调用
    public FlowLayout(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    //使用了自定属性
    public FlowLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);

        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);

        //warp_content 获得测量的高度和宽度
        int width = 0;
        int height = 0;

        //记录每一行的高度和宽度
        int lineHeight = 0;
        int lineWidth = 0;

        //获得每一个子View
        int childCount = getChildCount();
        for(int i=0;i<childCount;i++){
            View child = getChildAt(i);

            //测量子=View的宽和高
            measureChild(child,widthMeasureSpec,heightMeasureSpec);
            MarginLayoutParams lp = (MarginLayoutParams) child.getLayoutParams();

            int childWidth = child.getMeasuredWidth() + lp.leftMargin + lp.rightMargin;
            int childHeight = child.getMeasuredHeight() + lp.topMargin + lp.bottomMargin;

            if(lineWidth +childWidth > widthSize-getPaddingRight()-getPaddingLeft()){
                //对比得到最大宽度
                width = Math.max(width,lineWidth);
                //重置LineWidth
                lineWidth = childWidth;
                //记录行高
                height += lineHeight;
                lineHeight = childHeight;

            }else {
                lineHeight = Math.max(lineHeight,childHeight);
                lineWidth += childWidth;
            }

            if(i == childCount-1){
                width = Math.max(lineWidth,width);
                height +=lineHeight;
            }
        }

        Log.i("Width---->",widthSize+"");
        Log.i("height----->",heightSize+"");
        //判断是什么模式
        setMeasuredDimension(widthMode == MeasureSpec.EXACTLY?widthSize:width+getPaddingRight()+getPaddingLeft(),heightMode == MeasureSpec.EXACTLY?heightSize:height+getPaddingBottom()+getPaddingTop());


    }

    /**
     * 与当前Layout对应的LayoutParams
     * @param attrs
     * @return
     */
    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(),attrs);
    }

    /**
     * 用于存储所有的View
     */
    private List<List<View>> mAllViews = new ArrayList<>();

    /**
     * 每一行的高度
     */
    private List<Integer> mLineHeight= new ArrayList<>();

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        mAllViews.clear();
        mLineHeight.clear();


        //当前ViewGroup的宽度
        int width = getWidth();

        int lineWidth = 0;
        int lineheight = 0;

        List<View> lineViews = new ArrayList<>();

        int count = getChildCount();

        for (int i= 0;i<count;i++){

            View child = getChildAt(i);

            MarginLayoutParams lp = (MarginLayoutParams) child.getLayoutParams();

            int childWidht = child.getMeasuredWidth();
            int childHeight = child.getMeasuredHeight();

            //换行

            if(childWidht + lineWidth +lp.leftMargin + lp.rightMargin >width-getPaddingRight() - getPaddingLeft()){
                //记录LineHeight
                mLineHeight.add(lineheight);
                //记录当前的Views
                mAllViews.add(lineViews);

                //重置行宽和行高
                lineWidth = 0;
                lineheight = childHeight+lp.topMargin + lp.bottomMargin;

                //重置View的集合
                lineViews = new ArrayList<>();

            }

            // needn't enter
            lineWidth +=childWidht + lp.rightMargin + lp.leftMargin;
            lineheight = Math.max(lineheight,childHeight+lp.topMargin+lp.bottomMargin);

            lineViews.add(child);
        }

        //处理最后一行
        mLineHeight.add(lineheight);
        mAllViews.add(lineViews);

        //set child view location
        int left = getPaddingLeft();
        int top = getPaddingTop();
        int lineNum = mAllViews.size();
        for(int i=0;i<lineNum;i++){
            // all view for this line
            lineViews = mAllViews.get(i);
            lineheight = mLineHeight.get(i);

            for(int j=0;j<lineViews.size();j++){
                View child = lineViews.get(j);

                if(child.getVisibility() == View.GONE){
                    continue;
                }
                MarginLayoutParams lp = (MarginLayoutParams) child.getLayoutParams();
                int lc = left + lp.leftMargin;
                int tc = top + lp.topMargin;
                int rc = lc + child.getMeasuredWidth();
                int bc = tc+child.getMeasuredHeight();

                child.layout(lc,tc,rc,bc);

                left += child.getMeasuredWidth()+lp.leftMargin+lp.rightMargin;
            }
            left = getPaddingLeft() ;
            top += mLineHeight.get(i);
        }

    }






























}
